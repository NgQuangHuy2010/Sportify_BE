package com.sportify.service.entities;

import com.sportify.service.enums.FeedbackStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "feedbacks")
public class Feedback extends AbstractEntity {

	private static final long serialVersionUID = -7126465690955358016L;

	@Column(name = "subject")
    private String subject;

    @Column(name = "message")
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private FeedbackStatus status;
    
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserProfile userProfile;
}
