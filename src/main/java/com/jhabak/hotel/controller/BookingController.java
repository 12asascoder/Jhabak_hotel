package com.jhabak.hotel.controller;

import com.jhabak.hotel.model.Booking;
import com.jhabak.hotel.repository.BookingRepository;
import com.jhabak.hotel.repository.CustomerRepository;
import com.jhabak.hotel.repository.RoomRepository;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/bookings")
public class BookingController {
    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final CustomerRepository customerRepository;

    public BookingController(BookingRepository bookingRepository, RoomRepository roomRepository, CustomerRepository customerRepository) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("bookings", bookingRepository.findAll());
        model.addAttribute("rooms", roomRepository.findAll());
        model.addAttribute("customers", customerRepository.findAll());
        model.addAttribute("booking", new Booking());
        return "bookings/list";
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
    public String create(@RequestParam Long roomId,
                         @RequestParam Long customerId,
                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkIn,
                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOut,
                         Model model) {
        if (checkOut.isBefore(checkIn)) {
            model.addAttribute("error", "Check-out must be after check-in");
            return list(model);
        }
        Booking booking = new Booking();
        roomRepository.findById(roomId).ifPresent(booking::setRoom);
        customerRepository.findById(customerId).ifPresent(booking::setCustomer);
        booking.setCheckIn(checkIn);
        booking.setCheckOut(checkOut);
        bookingRepository.save(booking);
        return "redirect:/bookings";
    }

    @PostMapping("/{id}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable Long id) {
        bookingRepository.deleteById(id);
        return "redirect:/bookings";
    }
}


