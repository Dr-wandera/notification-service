package com.wanderaTech.notification_service.EventConsumer;

import com.wanderaTech.common_events.NotificationEvent.OrderPlacedEvent;
import com.wanderaTech.common_events.NotificationEvent.SellerNotificationEvent;
import com.wanderaTech.notification_service.Service.NotificationServiceImplementation;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationEventConsumer {

    private final NotificationServiceImplementation notificationService;

    //this listen to the order-topic to notify customer of product  item purchased
    @KafkaListener(topics = "order-topic", groupId = "notification-group")
    public void consumeOrderTopic(OrderPlacedEvent orderPlacedEvent) {
        notificationService.processCustomerNotification(orderPlacedEvent);
    }

    //this listen to the order-topic to notify seller of product  item purchased
    @KafkaListener(topics ="sellerNotification-topic", groupId = "notification-group")
    public  void consumeSellerTopic(SellerNotificationEvent sellerNotificationEvent) throws MessagingException {
        notificationService.processSellerNotification(sellerNotificationEvent);
    }
}
