package com.wanderaTech.notification_service.Service;

import com.wanderaTech.common_events.NotificationEvent.OrderPlacedEvent;
import com.wanderaTech.common_events.NotificationEvent.SellerNotificationEvent;
import com.wanderaTech.common_events.RegistrationEvent.RegisterNotificationEvent;
import com.wanderaTech.common_events.RegistrationEvent.RegistrationOtpResendEvent;
import com.wanderaTech.notification_service.Enum.NotificationStatus;
import com.wanderaTech.notification_service.Model.Notification;
import com.wanderaTech.notification_service.Repository.NotificationRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NotificationServiceImplementation  {

    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    //  process    customer notification of order placed
    public void processCustomerNotification(OrderPlacedEvent event) {

        Notification notification = Notification.builder()
                .userId(event.getUserId())
                .email(event.getEmail())
                .firstName(event.getFirstName())
                .status(NotificationStatus.PENDING)
                .retryCount(0)
                .createdAt(LocalDateTime.now())
                .build();

        notificationRepository.save(notification);

        try {

            //  Prepare template variables
            Map<String, Object> variables = new HashMap<>();
            variables.put("firstName", event.getFirstName());
            variables.put("orderId", event.getOrderNumber());
            variables.put("items", event.getItems());
            variables.put("totalAmount", event.getTotalAmount());

            emailService.sendEmailToCustomer(
                    event.getEmail(),
                    "Order Item purchased",
                    "customer-confirmation.html",
                    variables
            );

            notification.setStatus(NotificationStatus.SENT);
            notification.setSentAt(LocalDateTime.now());

        } catch (MessagingException e) {

            notification.setStatus(NotificationStatus.FAILED);
            notification.setRetryCount(notification.getRetryCount() + 1);
        }

        notificationRepository.save(notification);
    }

    // process seller  notification   of product sold
    public void processSellerNotification(SellerNotificationEvent sellerNotificationEvent) throws MessagingException {
        //  Prepare template variables
        Map<String, Object> variables = new HashMap<>();
        variables.put("sellerId", sellerNotificationEvent.getUserId());
        variables.put("orderNumber", sellerNotificationEvent.getOrderNumber());
        variables.put("items", sellerNotificationEvent.getItems());
        variables.put("createdAt", sellerNotificationEvent.getCreatedAt());
        variables.put("customerEmail", sellerNotificationEvent.getEmail());
        variables.put("customerPhoneNumber", sellerNotificationEvent.getPhoneNumber());


        emailService.sendEmailToSeller(
                sellerNotificationEvent.getSellerEmail(),
                "You made a sale",
                "seller-notification.html",
                variables
        );
    }

    //process  registered user notification
    public void processUserRegisteredNotification(RegisterNotificationEvent registerNotificationEvent) throws MessagingException {
        //  Prepare template variables
        Map<String, Object> variables = new HashMap<>();
        variables.put("lastName", registerNotificationEvent.getLastName());
        variables.put("otpCode", registerNotificationEvent.getOtpCode());

        emailService.sendEmailToRegisteredUser(
                registerNotificationEvent.getEmail(),
                "You have registered successful",
                "User-notification.html",
                variables
        );
    }

    //process resend otp
    public void processResendOtp(RegistrationOtpResendEvent registrationOtpResendEvent) throws MessagingException {
        //  Prepare template variables
        Map<String, Object> variables = new HashMap<>();
        variables.put("lastName", registrationOtpResendEvent.getLastName());
        variables.put("otpCode", registrationOtpResendEvent.getOtpCode());



        emailService.sendResendOtpEmail(
                registrationOtpResendEvent.getEmail(),
                "You have registered successful",
                "ResendOtp-notification.html",
                variables
        );
    }
}