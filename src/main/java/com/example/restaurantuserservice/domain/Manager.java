package com.example.restaurantuserservice.domain;

import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class Manager extends User{
    private String restaurantName;
    private LocalDate dateOfReg;

    public Manager() {
    }

    public Manager(String restaurantName, LocalDate dateOfReg) {
        this.restaurantName = restaurantName;
        this.dateOfReg = dateOfReg;
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
