package com.sportify.service.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sportify.service.dtos.ConnectionRequestDTO;
import com.sportify.service.dtos.ListUserDTO;
import com.sportify.service.entities.ChatRoom;
import com.sportify.service.entities.ConnectionRequest;
import com.sportify.service.entities.UserProfile;
import com.sportify.service.enums.ConnectionRequestStatus;
import com.sportify.service.repositories.ConnectionRequestRepository;
import com.sportify.service.repositories.UserProfileRepository;
import com.sportify.service.security.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConnectionRequestService {

    private final ConnectionRequestRepository connectionRequestRepository;
    private final UserProfileRepository userProfileRepository;
    private final ChatRoomService chatRoomService;
    private final MessageService messageService;
    private final JwtService jwtService;

    // Gửi yêu cầu kết nối
    public String sendConnectionRequest(String token, Long receiverId) {
        String email = jwtService.extractEmail(token);
        UserProfile sender = userProfileRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserProfile receiver = userProfileRepository.findById(receiverId)
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        var existingRequest = connectionRequestRepository.findBySenderAndReceiver(sender, receiver);

        // Nếu đã có request và trạng thái không phải DECLINED thì không cho gửi lại
        if (existingRequest.isPresent() && existingRequest.get().getStatus() != ConnectionRequestStatus.DECLINED) {
            return "Connection request already sent!";
        }

        // Nếu request tồn tại và bị từ chối, xóa request cũ trước khi tạo mới
        existingRequest.ifPresent(connectionRequestRepository::delete);

        ConnectionRequest request = new ConnectionRequest();
        request.setSender(sender);
        request.setReceiver(receiver);
        request.setStatus(ConnectionRequestStatus.PENDING);
        request.setSentAt(LocalDateTime.now());

        connectionRequestRepository.save(request);
        return "Connection request sent successfully!";
    }

    // Chấp nhận yêu cầu kết nối
    public String acceptConnectionRequest(Long requestId) {
        ConnectionRequest request = connectionRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        request.setStatus(ConnectionRequestStatus.ACCEPTED);
        request.setRespondedAt(LocalDateTime.now());
        connectionRequestRepository.save(request);
        // Them chatRoom
        UserProfile user1 = request.getSender();
        UserProfile user2 = request.getReceiver();
        ChatRoom chatRoom = chatRoomService.getOrCreateChatRoom(user1.getId(), user2.getId());
        messageService.sendMessage(chatRoom.getId(), user2.getId(), "We are friend now");
        return "Connection request accepted!";
    }

    // Từ chối yêu cầu kết nối
    public String rejectConnectionRequest(Long requestId) {
        ConnectionRequest request = connectionRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        request.setStatus(ConnectionRequestStatus.DECLINED);
        request.setRespondedAt(LocalDateTime.now());

        connectionRequestRepository.save(request);
        return "Connection request rejected!";
    }

    // Lấy danh sách yêu cầu kết nối đang chờ
    public List<ConnectionRequestDTO> getPendingRequests(String token) {
        String email = jwtService.extractEmail(token);
        UserProfile user = userProfileRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<ConnectionRequest> requests = connectionRequestRepository.findByReceiverAndStatus(user, ConnectionRequestStatus.PENDING);
        return requests.stream().map(ConnectionRequestDTO::new).collect(Collectors.toList());
    }

    // Lấy danh sách bạn bè đã kết nối
    public List<ListUserDTO> getConnectedUsers(String token) {
        String email = jwtService.extractEmail(token);
        UserProfile user = userProfileRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<ConnectionRequest> acceptedRequests = connectionRequestRepository.findBySenderAndStatus(user, ConnectionRequestStatus.ACCEPTED);
        acceptedRequests.addAll(connectionRequestRepository.findByReceiverAndStatus(user, ConnectionRequestStatus.ACCEPTED));

        List<UserProfile> connectedProfiles =  acceptedRequests.stream()
                .map(req -> req.getSender().equals(user) ? req.getReceiver() : req.getSender())
                .collect(Collectors.toList());
        return connectedProfiles.stream().map(ListUserDTO::new).collect(Collectors.toList());
    }
    
    public String cancelConnectionRequest(String token, Long receiverId) {
       
        String email = jwtService.extractEmail(token);
        UserProfile sender = userProfileRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserProfile receiver = userProfileRepository.findById(receiverId)
                .orElseThrow(() -> new RuntimeException("Receiver not found"));
        var existingRequest = connectionRequestRepository.findBySenderAndReceiver(sender, receiver);
        if (existingRequest.isEmpty()) {
            return "No connection request found to cancel!";
        }
        connectionRequestRepository.delete(existingRequest.get());
        return "Connection request has been canceled!";
    }
    
 // Lấy danh sách yêu cầu kết nối đã gửi nhưng chưa được chấp nhận
    public List<ConnectionRequestDTO> getOutgoingRequests(String token) {
        String email = jwtService.extractEmail(token);
        UserProfile user = userProfileRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<ConnectionRequest> sentRequests = connectionRequestRepository.findBySenderAndStatus(user, ConnectionRequestStatus.PENDING);
        return sentRequests.stream().map(ConnectionRequestDTO::new).collect(Collectors.toList());
    }
    
}