package com.sinhaanurag.notifications.service;

import java.time.LocalDateTime;

import com.sinhaanurag.clients.notification.NotificationRequest;
import com.sinhaanurag.notifications.dao.NotificationRepository;
import com.sinhaanurag.notifications.model.Notification;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationService {
	private NotificationRepository notificationRepository;
	public void send(NotificationRequest notificationRequest) {
		notificationRepository.save(Notification.builder()
				.toCustomerId(notificationRequest.getToCustomerId())
				.toCustomerEmail(notificationRequest.getToCustomerName())
				.sender("Sinhaanurag")
				.message(notificationRequest.getMessage())
				.sentAt(LocalDateTime.now())
				.build());
	}
}
