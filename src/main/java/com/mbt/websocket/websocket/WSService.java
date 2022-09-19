package com.mbt.websocket.websocket;

import com.mbt.websocket.websocket.dto.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WSService {

    private final SimpMessagingTemplate simpMessagingTemplate;

    private final NotificationService notificationService;

    @Autowired
    public WSService(SimpMessagingTemplate simpMessagingTemplate, NotificationService notificationService) {
        this.simpMessagingTemplate = simpMessagingTemplate;

        this.notificationService = notificationService;
    }

    public void notifyFrontend(final String message) {
        ResponseMessage responseMessage = new ResponseMessage(message);
        this.notificationService.sendGlobalNotification();
        simpMessagingTemplate.convertAndSend("/topic/messages", responseMessage);
    }

    public void notifyUser(String userId, String messageContent) {
        ResponseMessage responseMessage = new ResponseMessage(messageContent);
        this.notificationService.sendPrivateNotification(userId);
        simpMessagingTemplate.convertAndSendToUser(userId, "/topic/private-messages", responseMessage);
    }
}
