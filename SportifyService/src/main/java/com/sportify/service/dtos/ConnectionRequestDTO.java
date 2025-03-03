package com.sportify.service.dtos;

import java.time.LocalDateTime;

import com.sportify.service.entities.ConnectionRequest;
import com.sportify.service.enums.ConnectionRequestStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConnectionRequestDTO {
    private Long id;
    private ListUserDTO sender;
    private ListUserDTO receiver;
    private ConnectionRequestStatus status;
    private LocalDateTime sentAt;
    private LocalDateTime respondedAt;
    

    public ConnectionRequestDTO(ConnectionRequest request) {
        this.id = request.getId();
        this.sender = new ListUserDTO(request.getSender());
        this.receiver = new ListUserDTO(request.getReceiver());
        this.status = request.getStatus();
        this.sentAt = request.getSentAt();
        this.respondedAt = request.getRespondedAt();
    }
}
