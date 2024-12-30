package com.example.restaurantuserservice.domain;

import jakarta.persistence.Entity;

@Entity
public class Client extends User{
    private Integer numberOfReservation;

    public Client() {
    }

    public Client(Integer numberOfReservation) {
        this.numberOfReservation = numberOfReservation;
    }

    public Integer getNumberOfReservation() {
        return numberOfReservation;
    }

    public void setNumberOfReservation(Integer numberOfReservation) {
        this.numberOfReservation = numberOfReservation;
    }
}
