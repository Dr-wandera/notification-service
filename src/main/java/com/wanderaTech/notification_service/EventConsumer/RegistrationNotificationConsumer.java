package com.wanderaTech.notification_service.EventConsumer;

import com.wanderaTech.common_events.RegistrationEvent.RegisterNotificationEvent;
import com.wanderaTech.notification_service.Service.NotificationServiceImplementation;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegistrationNotificationConsumer {
    private final NotificationServiceImplementation notificationService;

    // listen to  register-topic to give otp for account activation
    @KafkaListener(topics = "register-topic", groupId = "notification-group")
    public void consumeOrderTopic(RegisterNotificationEvent registerNotificationEvent) throws MessagingException {
        notificationService.processUserRegisteredNotification(registerNotificationEvent);
        log.info("LAST NAME: {} OTP CODE: {}", registerNotificationEvent.getLastName(), registerNotificationEvent.getOtpCode());

    }
}
