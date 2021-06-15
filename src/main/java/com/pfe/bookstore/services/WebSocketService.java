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

    public void followNotification(final Long topicSuffix) {
        messagingTemplate.convertAndSend("/follow/" + topicSuffix, "Default message from our WS service");
    }

    public void followNotification(final Long topicSuffix, final String payload) {
        messagingTemplate.convertAndSend("/follow/" + topicSuffix, payload);
    }
    public void newBookNotification(final Long topicSuffix) {
        messagingTemplate.convertAndSend("/newBook/" + topicSuffix, "Default message from our WS service");
    }

    public void newBookNotification(final Long topicSuffix, final String payload) {
        messagingTemplate.convertAndSend("/newBook/" + topicSuffix, payload);
    }
}
