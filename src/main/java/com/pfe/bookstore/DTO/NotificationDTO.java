package com.pfe.bookstore.DTO;

import lombok.Data;

@Data
public class NotificationDTO {
    private Long id;
    private String notification;
    private Long userId;
    private boolean seen;
}
