package com.jhabak.hotel.repository;

import com.jhabak.hotel.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}


