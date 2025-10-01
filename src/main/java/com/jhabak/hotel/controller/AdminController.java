package com.jhabak.hotel.controller;

import com.jhabak.hotel.model.Booking;
import com.jhabak.hotel.model.Customer;
import com.jhabak.hotel.model.Expense;
import com.jhabak.hotel.repository.BookingRepository;
import com.jhabak.hotel.repository.CustomerRepository;
import com.jhabak.hotel.repository.ExpenseRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    private final BookingRepository bookingRepository;
    private final ExpenseRepository expenseRepository;
    private final CustomerRepository customerRepository;

    public AdminController(BookingRepository bookingRepository, ExpenseRepository expenseRepository, CustomerRepository customerRepository) {
        this.bookingRepository = bookingRepository;
        this.expenseRepository = expenseRepository;
        this.customerRepository = customerRepository;
    }

    @GetMapping("/reminders")
    public String reminders(Model model) {
        LocalDate today = LocalDate.now();
        List<Booking> dueCheckout = bookingRepository.findAll().stream()
                .filter(b -> today.equals(b.getCheckOut()))
                .toList();
        model.addAttribute("dueCheckout", dueCheckout);
        return "admin/reminders";
    }

    @GetMapping("/revenue")
    public String revenue(@RequestParam(required = false) String from,
                          @RequestParam(required = false) String to,
                          Model model) {
        LocalDate start = from != null ? LocalDate.parse(from) : LocalDate.now().withDayOfMonth(1);
        LocalDate end = to != null ? LocalDate.parse(to) : LocalDate.now();
        List<Expense> items = expenseRepository.findByDateBetween(start, end);
        BigDecimal totalExpenses = items.stream()
                .filter(i -> "EXPENSE".equalsIgnoreCase(i.getKind()))
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalCharges = items.stream()
                .filter(i -> "CHARGE".equalsIgnoreCase(i.getKind()))
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        model.addAttribute("items", items);
        model.addAttribute("totalExpenses", totalExpenses);
        model.addAttribute("totalCharges", totalCharges);
        model.addAttribute("net", totalCharges.subtract(totalExpenses));
        model.addAttribute("customers", customerRepository.findAll());
        return "admin/revenue";
    }

    @PostMapping("/revenue")
    public String addItem(@RequestParam BigDecimal amount,
                          @RequestParam String kind,
                          @RequestParam(required = false) String description,
                          @RequestParam(required = false) Long customerId,
                          @RequestParam(required = false) String date) {
        Expense e = new Expense();
        e.setAmount(amount);
        e.setKind(kind);
        e.setDescription(description);
        e.setDate(date != null ? LocalDate.parse(date) : LocalDate.now());
        if (customerId != null) {
            customerRepository.findById(customerId).ifPresent(e::setCustomer);
        }
        expenseRepository.save(e);
        return "redirect:/admin/revenue";
    }
}


