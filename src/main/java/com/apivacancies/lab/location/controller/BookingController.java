/*
 * Copyright (c) 2021.
 * Abdelhadi Hasnaoui <abdelhadi.hasnaoui@protonmail.com>
 * sinjin.tech
 */

package com.apivacancies.lab.location.controller;

import com.apivacancies.lab.location.domain.Booking;
import com.apivacancies.lab.location.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RestController("/rest/api/booking")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/booking")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Booking> createBooking(Long appart_id, @RequestBody Booking booking) {
        Booking createdBook = bookingService.createCalendar(appart_id, booking);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/appart-id")
                .buildAndExpand(createdBook.getId())
                .toUri();

        return ResponseEntity.created(uri).body(createdBook);
    }

    @GetMapping("/booking/appart")
    @ResponseStatus(HttpStatus.OK)
    public List<Booking> findBookingByApartId(Long appart_id) {
        return bookingService.findBookingByAppart(appart_id);
    }

    @GetMapping("/booking/appart/check-inOrder")
    @ResponseStatus(HttpStatus.OK)
    public List<Booking> findBookingByApartInCheckingOrder(Long appart_id) {
        return bookingService.findBookingsByAppartInCheckInOrder(appart_id);
    }

    @GetMapping("/bookings")
    @ResponseStatus(HttpStatus.OK)
    public List<Booking> getAllBooking() {
        return bookingService.getBookings();
    }
}
