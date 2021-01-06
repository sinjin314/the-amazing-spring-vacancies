/*
 * Copyright (c) 2021.
 * Abdelhadi Hasnaoui <abdelhadi.hasnaoui@protonmail.com>
 * sinjin.tech
 */

package com.apivacancies.lab.location.service;

import com.apivacancies.lab.location.Error.Apartment.ApartmentNotFoundException;
import com.apivacancies.lab.location.domain.Apartment;
import com.apivacancies.lab.location.domain.Booking;
import com.apivacancies.lab.location.repository.ApartmentRepository;
import com.apivacancies.lab.location.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final ApartmentRepository apartmentRepository;

    public BookingService(BookingRepository bookingRepository, ApartmentRepository apartmentRepository) {
        this.bookingRepository = bookingRepository;
        this.apartmentRepository = apartmentRepository;
    }

    public Booking createCalendar(Long id, Booking booking) {
        Apartment apartment = getApartment(id);

        apartment.addBooking(booking);

        apartmentRepository.save(apartment);

        return booking;
    }

    public List<Booking> findBookingByAppart(Long id) {
        Apartment apartment = getApartment(id);

        List<Booking> bookings = bookingRepository.findBookingByAppart(id);

        return bookings;
    }

    public List<Booking> findBookingsByAppartInCheckInOrder(Long id) {
        Apartment apartment = getApartment(id);

        List<Booking> bookings = bookingRepository.findBookingsByAppartInCheckInOrder(id);

        return bookings;
    }

    public Apartment getApartment(Long id) {
        Optional<Apartment> apartment = apartmentRepository.findById(id);

        if (!apartment.isPresent()) {
            throw new ApartmentNotFoundException(id);
        } else {
            return apartment.get();
        }
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    public List<Booking> getBookings() {
       return bookingRepository.findAll();
    }
}
