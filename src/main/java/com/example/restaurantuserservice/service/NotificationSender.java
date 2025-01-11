package com.example.restaurantuserservice.service;

import com.example.restaurantuserservice.dto.NotificationDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationSender {
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    public void sendNotification(NotificationDto notificationDto){
        try{
            String jsonNotification = objectMapper.writeValueAsString(notificationDto);
            jmsTemplate.convertAndSend("notificationQueue", jsonNotification);
            System.out.println("Poslata notifikacija tipa: " + notificationDto.getNotificationType());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
