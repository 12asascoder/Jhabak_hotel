package com.jhabak.hotel.controller;

import com.jhabak.hotel.model.Room;
import com.jhabak.hotel.repository.RoomRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rooms")
public class RoomController {
    private final RoomRepository roomRepository;

    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("rooms", roomRepository.findAll());
        model.addAttribute("room", new Room());
        return "rooms/list";
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String create(@Valid @ModelAttribute("room") Room room, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("rooms", roomRepository.findAll());
            return "rooms/list";
        }
        roomRepository.save(room);
        return "redirect:/rooms";
    }

    @PostMapping("/{id}/toggle")
    @PreAuthorize("hasRole('ADMIN')")
    public String toggle(@PathVariable Long id) {
        roomRepository.findById(id).ifPresent(r -> {
            r.setAvailable(!Boolean.TRUE.equals(r.getAvailable()));
            roomRepository.save(r);
        });
        return "redirect:/rooms";
    }

    @PostMapping("/{id}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable Long id) {
        roomRepository.deleteById(id);
        return "redirect:/rooms";
    }
}


