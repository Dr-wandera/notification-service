package com.wanderaTech.notification_service.EventConsumer;

import com.wanderaTech.common_events.RegistrationEvent.RegisterNotificationEvent;
import com.wanderaTech.notification_service.Service.NotificationServiceImplementation;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RegisterResendOtpConsumer {
    private final NotificationServiceImplementation notificationService;

    // listen to  register-topic to give otp for account activation
    @KafkaListener(topics = "resendOtp-topic", groupId = "notification-group")
    public void consumeResendTopic(RegisterNotificationEvent registerNotificationEvent) throws MessagingException {
        notificationService.processResendOtp(registerNotificationEvent);
    }
}
