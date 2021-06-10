package com.pfe.bookstore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@Service
@CrossOrigin("*")
public class WebSocketService {
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public WebSocketService(final SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendMessage(final Long topicSuffix) {
        messagingTemplate.convertAndSend("/notification/" + topicSuffix, "Default message from our WS service");
    }

    public void sendMessage(final Long topicSuffix, final String payload) {
        messagingTemplate.convertAndSend("/notification/" + topicSuffix, payload);
    }
}
