package com.jhabak.hotel.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Room room;

    @ManyToOne(optional = false)
    private Customer customer;

    @NotNull
    @FutureOrPresent
    private LocalDate checkIn;

    @NotNull
    @FutureOrPresent
    private LocalDate checkOut;

    @Column(length = 500)
    private String serviceRequest;

    private String createdBy; // username who created the booking

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public LocalDate getCheckIn() { return checkIn; }
    public void setCheckIn(LocalDate checkIn) { this.checkIn = checkIn; }
    public LocalDate getCheckOut() { return checkOut; }
    public void setCheckOut(LocalDate checkOut) { this.checkOut = checkOut; }
    public String getServiceRequest() { return serviceRequest; }
    public void setServiceRequest(String serviceRequest) { this.serviceRequest = serviceRequest; }
    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
}


