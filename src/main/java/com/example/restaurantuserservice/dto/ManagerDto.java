package com.example.restaurantuserservice.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class ManagerDto extends UserDto{

    private String restaurantName;
    private LocalDate dateOfReg;

    public ManagerDto() {
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public LocalDate getDateOfReg() {
        return dateOfReg;
    }

    public void setDateOfReg(LocalDate dateOfReg) {
        this.dateOfReg = dateOfReg;
    }

}
