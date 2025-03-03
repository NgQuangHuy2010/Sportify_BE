package com.sportify.service.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sportify.service.entities.ConnectionRequest;
import com.sportify.service.entities.UserProfile;
import com.sportify.service.enums.ConnectionRequestStatus;

@Repository
public interface ConnectionRequestRepository extends JpaRepository<ConnectionRequest, Long> {
    
    Optional<ConnectionRequest> findBySenderAndReceiver(UserProfile sender, UserProfile receiver);

    List<ConnectionRequest> findByReceiverAndStatus(UserProfile receiver, ConnectionRequestStatus status);

    List<ConnectionRequest> findBySenderAndStatus(UserProfile sender, ConnectionRequestStatus status);
}
