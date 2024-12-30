package com.example.restaurantuserservice.dto;

public class ClientDto extends UserDto{
    private Integer numberOfReservation;

    public ClientDto() {
    }

    public ClientDto(Integer numberOfReservation) {
        this.numberOfReservation = numberOfReservation;
    }

    public Integer getNumberOfReservation() {
        return numberOfReservation;
    }

    public void setNumberOfReservation(Integer numberOfReservation) {
        this.numberOfReservation = numberOfReservation;
    }
}
