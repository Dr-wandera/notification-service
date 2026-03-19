package com.wanderaTech.notification_service.Model;

import com.wanderaTech.notification_service.Enum.NotificationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerId;
    private String orderNumber;

    private String email;
    private String  firstName;

    private String message;

    @Enumerated(EnumType.STRING)
    private NotificationStatus status;

    private int retryCount;

    private LocalDateTime createdAt;

    private LocalDateTime sentAt;
}