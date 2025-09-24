package com.jhabak.hotel.config;

import com.jhabak.hotel.model.Booking;
import com.jhabak.hotel.model.Customer;
import com.jhabak.hotel.model.Room;
import com.jhabak.hotel.repository.BookingRepository;
import com.jhabak.hotel.repository.CustomerRepository;
import com.jhabak.hotel.repository.RoomRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;

@Configuration
@Profile("!mysql")
public class DataSeeder {

    @Bean
    ApplicationRunner seed(RoomRepository rooms, CustomerRepository customers, BookingRepository bookings) {
        return args -> {
            if (rooms.count() == 0) {
                Room r1 = new Room(); r1.setNumber("101"); r1.setType("Single"); r1.setPricePerNight(99.0); r1.setAvailable(true);
                Room r2 = new Room(); r2.setNumber("102"); r2.setType("Double"); r2.setPricePerNight(149.0); r2.setAvailable(true);
                Room r3 = new Room(); r3.setNumber("201"); r3.setType("Suite"); r3.setPricePerNight(249.0); r3.setAvailable(true);
                rooms.save(r1); rooms.save(r2); rooms.save(r3);
            }
            if (customers.count() == 0) {
                Customer c1 = new Customer(); c1.setName("John Doe"); c1.setEmail("john@example.com"); c1.setPhone("111-222-3333");
                Customer c2 = new Customer(); c2.setName("Jane Smith"); c2.setEmail("jane@example.com"); c2.setPhone("444-555-6666");
                customers.save(c1); customers.save(c2);
            }
            if (bookings.count() == 0) {
                rooms.findByNumber("101").ifPresent(room -> customers.findByEmail("john@example.com").ifPresent(c -> {
                    Booking b = new Booking();
                    b.setRoom(room);
                    b.setCustomer(c);
                    b.setCheckIn(LocalDate.now());
                    b.setCheckOut(LocalDate.now().plusDays(2));
                    bookings.save(b);
                }));
            }
        };
    }
}


