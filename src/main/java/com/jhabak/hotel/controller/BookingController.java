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
        boolean isAdmin = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        if (isAdmin) {
            model.addAttribute("bookings", bookingRepository.findAll());
        } else {
            String user = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName();
            model.addAttribute("bookings", bookingRepository.findByCreatedBy(user));
        }
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
                         @RequestParam(required = false) String serviceRequest,
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
        booking.setServiceRequest(serviceRequest);
        booking.setCreatedBy(org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName());
        bookingRepository.save(booking);
        model.addAttribute("booking", booking);
        return "bookings/confirmation";
    }

    @GetMapping("/{id}/download")
    @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
    public void download(@PathVariable Long id, jakarta.servlet.http.HttpServletResponse response) throws java.io.IOException {
        Booking b = bookingRepository.findById(id).orElse(null);
        if (b == null) { response.sendError(404); return; }
        response.setContentType("text/plain");
        response.setHeader("Content-Disposition", "attachment; filename=booking-" + id + ".txt");
        String content = "Booking Confirmation\n" +
                "Reference: " + b.getId() + "\n" +
                "Room: " + (b.getRoom()!=null?b.getRoom().getNumber():"-") + "\n" +
                "Customer: " + (b.getCustomer()!=null?b.getCustomer().getName():"-") + "\n" +
                "Check-in: " + b.getCheckIn() + "\n" +
                "Check-out: " + b.getCheckOut() + "\n" +
                "Service request: " + (b.getServiceRequest()!=null?b.getServiceRequest():"-") + "\n";
        response.getOutputStream().write(content.getBytes(java.nio.charset.StandardCharsets.UTF_8));
        response.flushBuffer();
    }

    @PostMapping("/{id}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable Long id) {
        bookingRepository.deleteById(id);
        return "redirect:/bookings";
    }
}


