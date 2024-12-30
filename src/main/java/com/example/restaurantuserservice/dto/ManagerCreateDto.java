package com.example.restaurantuserservice.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class ManagerCreateDto extends UserCreateDto{

    @NotBlank
    private String restaurantName;
    @NotBlank
    private LocalDate dateOfReg;

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
