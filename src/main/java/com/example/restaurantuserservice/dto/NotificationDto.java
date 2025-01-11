package com.example.restaurantuserservice.dto;

public class NotificationDto {
    private Long userId;
    private String email;
    private String notificationType;

    public NotificationDto(Long userId, String email, String notificationType) {
        this.userId = userId;
        this.email = email;
        this.notificationType = notificationType;
    }

    public NotificationDto() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }
}
