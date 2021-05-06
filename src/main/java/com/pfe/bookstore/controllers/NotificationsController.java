package com.pfe.bookstore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationsController {
    @Autowired
    private  SimpMessagingTemplate template;

    @MessageMapping("/start")
    public void start(StompHeaderAccessor stompHeaderAccessor) {
//        template.add(stompHeaderAccessor.getSessionId());
    }
    @MessageMapping("/stop")
    public void stop(StompHeaderAccessor stompHeaderAccessor) {
//        template.remove(stompHeaderAccessor.getSessionId());
    }

}
