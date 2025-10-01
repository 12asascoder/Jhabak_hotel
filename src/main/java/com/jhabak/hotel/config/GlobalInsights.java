package com.jhabak.hotel.config;

import com.jhabak.hotel.repository.BookingRepository;
import com.jhabak.hotel.repository.RoomRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Component
public class GlobalInsights {
    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;

    public GlobalInsights(RoomRepository roomRepository, BookingRepository bookingRepository) {
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
    }

    @ModelAttribute("globalInsights")
    public Map<String, Object> addGlobalInsights() {
        long totalRooms = roomRepository.count();
        long occupiedRooms = roomRepository.countByAvailable(Boolean.FALSE);
        int occupancy = totalRooms > 0 ? (int) Math.round((occupiedRooms * 100.0) / totalRooms) : 0;

        LocalDate today = LocalDate.now();
        long bookingsToday = bookingRepository.findAll().stream()
                .filter(b -> (b.getCheckIn() != null && !today.isBefore(b.getCheckIn()))
                        && (b.getCheckOut() != null && !today.isAfter(b.getCheckOut())))
                .count();

        Map<String, Object> map = new HashMap<>();
        map.put("rooms", totalRooms);
        map.put("occupancy", occupancy);
        map.put("bookingsToday", bookingsToday);
        return map;
    }
}


