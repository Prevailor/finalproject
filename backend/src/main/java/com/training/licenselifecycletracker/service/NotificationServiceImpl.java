//package com.training.licenselifecycletracker.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class NotificationServiceImpl implements NotificationService {
//
//    private final SimpMessagingTemplate messagingTemplate;
//
//    @Autowired
//    public NotificationServiceImpl(SimpMessagingTemplate messagingTemplate) {
//        this.messagingTemplate = messagingTemplate;
//    }
//
//    @Override
//    public void sendGeneralNotification(String message) {
//        String destination = "/topic/generalNotifications";
//        messagingTemplate.convertAndSend(destination, message);
//    }
//}
