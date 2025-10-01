package com.jhabak.hotel.repository;

import com.jhabak.hotel.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByNumber(String number);

    // Availability helpers
    List<Room> findByAvailableTrue();
    List<Room> findByAvailableFalse();
    long countByAvailable(Boolean available);

    // Basic filters
    List<Room> findByTypeContainingIgnoreCase(String type);
    List<Room> findByPricePerNightBetween(Double minPrice, Double maxPrice);
}


