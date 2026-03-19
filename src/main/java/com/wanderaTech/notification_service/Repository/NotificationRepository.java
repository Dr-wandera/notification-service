package com.wanderaTech.notification_service.Repository;

import com.wanderaTech.notification_service.Enum.NotificationStatus;
import com.wanderaTech.notification_service.Model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {
    List<Notification> findByStatus(NotificationStatus status);

}
