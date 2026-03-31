package com.wanderaTech.notification_service.EventConsumer;

import com.wanderaTech.common_events.RegistrationEvent.RegisterNotificationEvent;
import com.wanderaTech.notification_service.Service.NotificationServiceImplementation;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegistrationNotificationConsumer {
    private final NotificationServiceImplementation notificationService;

    // listen to  register-topic to give otp for account activation
    @KafkaListener(topics = "register-topic", groupId = "notification-group")
    public void consumeOrderTopic(RegisterNotificationEvent registerNotificationEvent) throws MessagingException {
        notificationService.processUserRegisteredNotification(registerNotificationEvent);
    }
}
