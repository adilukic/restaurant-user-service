package com.example.restaurantuserservice.dto;

public class ClientCreateDto extends UserCreateDto{


    private Integer numberOfReservation;

    public Integer getNumberOfReservation() {
        return numberOfReservation;
    }

    public void setNumberOfReservation(Integer numberOfReservation) {
        this.numberOfReservation = numberOfReservation;
    }
}
