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
                Room r2 = new Room(); r2.setNumber("102"); r2.setType("Double"); r2.setPricePerNight(149.0); r2.setAvailable(false);
                Room r3 = new Room(); r3.setNumber("201"); r3.setType("Suite"); r3.setPricePerNight(249.0); r3.setAvailable(false);
                Room r4 = new Room(); r4.setNumber("202"); r4.setType("Single"); r4.setPricePerNight(109.0); r4.setAvailable(true);
                Room r5 = new Room(); r5.setNumber("203"); r5.setType("Double"); r5.setPricePerNight(159.0); r5.setAvailable(true);
                Room r6 = new Room(); r6.setNumber("301"); r6.setType("Suite"); r6.setPricePerNight(279.0); r6.setAvailable(true);
                rooms.save(r1); rooms.save(r2); rooms.save(r3); rooms.save(r4); rooms.save(r5); rooms.save(r6);
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
                    b.setServiceRequest("Late check-in, airport pickup");
                    bookings.save(b);
                }));
                rooms.findByNumber("102").ifPresent(room -> customers.findByEmail("jane@example.com").ifPresent(c -> {
                    Booking b = new Booking();
                    b.setRoom(room);
                    b.setCustomer(c);
                    b.setCheckIn(LocalDate.now());
                    b.setCheckOut(LocalDate.now());
                    b.setServiceRequest("Breakfast for two");
                    bookings.save(b);
                }));
                rooms.findByNumber("201").ifPresent(room -> customers.findByEmail("john@example.com").ifPresent(c -> {
                    Booking b = new Booking();
                    b.setRoom(room);
                    b.setCustomer(c);
                    b.setCheckIn(LocalDate.now());
                    b.setCheckOut(LocalDate.now());
                    b.setServiceRequest("Spa at 6 PM");
                    bookings.save(b);
                }));
            }
        };
    }
}


