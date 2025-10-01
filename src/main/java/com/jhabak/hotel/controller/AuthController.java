package com.jhabak.hotel.controller;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;

import com.jhabak.hotel.repository.RoomRepository;
import com.jhabak.hotel.repository.BookingRepository;
import java.time.LocalDate;

@Controller
public class AuthController {
    private final InMemoryUserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;
    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;

    public AuthController(InMemoryUserDetailsManager userDetailsManager, PasswordEncoder passwordEncoder,
                          RoomRepository roomRepository, BookingRepository bookingRepository) {
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
    }

    @GetMapping("/login")
    public String login(Model model) {
        long totalRooms = roomRepository.count();
        long occupiedRooms = roomRepository.countByAvailable(Boolean.FALSE);
        int occupancy = totalRooms > 0 ? (int) Math.round((occupiedRooms * 100.0) / totalRooms) : 0;

        LocalDate today = LocalDate.now();
        long bookingsToday = bookingRepository.findAll().stream()
                .filter(b -> (b.getCheckIn() != null && !today.isBefore(b.getCheckIn()))
                        && (b.getCheckOut() != null && !today.isAfter(b.getCheckOut())))
                .count();

        model.addAttribute("insightRooms", totalRooms);
        model.addAttribute("insightOccupancy", occupancy);
        model.addAttribute("insightBookingsToday", bookingsToday);
        return "auth/login";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        return "auth/signup";
    }

    @PostMapping("/signup")
    public String handleSignup(@RequestParam String username,
                               @RequestParam String password,
                               Model model,
                               HttpServletRequest request,
                               RedirectAttributes redirectAttributes) {
        if (userDetailsManager.userExists(username)) {
            model.addAttribute("error", "Username already exists");
            return "auth/signup";
        }
        UserDetails customer = User.withUsername(username)
                .password(passwordEncoder.encode(password))
                .roles("CUSTOMER")
                .build();
        userDetailsManager.createUser(customer);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                customer, null, customer.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        request.getSession(true); // ensure session is created so context is persisted
        redirectAttributes.addFlashAttribute("success", "Customer account created successfully");
        return "redirect:/bookings";
    }
}


