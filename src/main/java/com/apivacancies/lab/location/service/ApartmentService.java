/*
 * Copyright (c) 2021.
 * Abdelhadi Hasnaoui <abdelhadi.hasnaoui@protonmail.com>
 * sinjin.tech
 */

package com.apivacancies.lab.location.service;

import com.apivacancies.lab.location.Error.Apartment.ApartmentNotFoundException;
import com.apivacancies.lab.location.Error.Residency.ResidencyNotFoundException;
import com.apivacancies.lab.location.domain.Apartment;
import com.apivacancies.lab.location.domain.Booking;
import com.apivacancies.lab.location.domain.Residency;
import com.apivacancies.lab.location.repository.ApartmentRepository;
import com.apivacancies.lab.location.repository.BookingRepository;
import com.apivacancies.lab.location.repository.ResidencyRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ApartmentService {
    private final ApartmentRepository apartmentRepository;
    private final ResidencyRepository residencyRepository;
    private final ResidencyService residencyService;
    private final BookingRepository bookingRepository;


    public ApartmentService(ApartmentRepository apartmentRepository, ResidencyRepository residencyRepository, ResidencyService residencyService, BookingService bookingService, BookingRepository bookingRepository) {
        this.apartmentRepository = apartmentRepository;
        this.residencyRepository = residencyRepository;
        this.residencyService = residencyService;
        this.bookingRepository = bookingRepository;
    }

    public List<Apartment> getApartments() {
        return apartmentRepository.findAll();
    }

    public Apartment getApartment(Long id) {
        Optional<Apartment> apartment = apartmentRepository.findById(id);

        if (!apartment.isPresent()) {
            throw new ApartmentNotFoundException(id);
        } else {
            return apartment.get();
        }
    }

    public Apartment updateApartment(Long id, Apartment apartment) {
        Optional<Apartment> apartmentOptional = apartmentRepository.findById(id);

        if (!apartmentOptional.isPresent()) {
            throw new ApartmentNotFoundException(id);
        } else {
            apartmentRepository.findApartmentById(id);

            return apartmentRepository.save(apartment);
        }
    }

    public void deleteApartment(Long id) {
        Optional<Apartment> apartmentOptional = apartmentRepository.findById(id);

        if (!apartmentOptional.isPresent()) {
            throw new ApartmentNotFoundException(id);
        } else {
            apartmentRepository.deleteById(id);
        }
    }

    public Apartment createApartment(Long residency_id, Apartment apartment) {
        Optional<Residency> residencyOptional = residencyRepository.findById(residency_id);

        if (!residencyOptional.isPresent()) {
            throw new ResidencyNotFoundException(residency_id);
        } else {
            Residency residency = residencyOptional.get();

            residency.addApartment(apartment);

            residencyRepository.save(residency);

            return apartment;
        }
    }

    public List<Apartment> getApartmentByRegion(String region) {
        List<Residency> residencies = residencyService.findResidencyByRegion(region);
        List<Apartment> apartments = new ArrayList<>();

        for (Residency residency : residencies) {
            apartments.addAll(residency.getApartments());
        }

        return apartments;
    }

    public List<Apartment> getApartmentWithPool() {
        List<Residency> residencies = residencyService.findResidencyWithPool();
        List<Apartment> apartments = new ArrayList<>();

        for (Residency residency : residencies) {
            apartments.addAll(residency.getApartments());
        }

        return apartments;
    }

    public List<Apartment> getApartmentAtMountain() {
        List<Residency> residencies = residencyService.findResidencyAtMountain();
        List<Apartment> apartments = new ArrayList<>();

        for (Residency residency : residencies) {
            apartments.addAll(residency.getApartments());
        }

        return apartments;
    }

    public List<Apartment> findAvailableApartBetweenDate(LocalDate checkIn, LocalDate checkOut) {
        List<Apartment> apartments = apartmentRepository.findAll();
        List<Booking> apartBooking = new ArrayList<>();
        List<Apartment> availableApart = new ArrayList<>();


        for (Apartment apart : apartments) {
            apartBooking = bookingRepository.findBookingsByAppartInCheckInOrder(apart.getId());

            if (apartBooking.size() >= 2) {
                if (isAvailable(checkIn, checkOut, apartBooking)) {
                    availableApart.add(apart);
                }
            } else if (apartBooking.size() == 0) {
                availableApart.add(apart);
            } else {
                Booking book = apartBooking.get(0);
                if (checkIn.isBefore(book.getCheckIn()) || checkIn.isAfter(book.getCheckOut())) {
                    if (checkIn.isBefore(book.getCheckIn()) || checkIn.isAfter(book.getCheckOut())) {
                        if (checkOut.isBefore(book.getCheckIn()) || checkOut.isAfter(book.getCheckOut())) {
                            if (checkOut.isBefore(book.getCheckIn()) || checkOut.isAfter(book.getCheckOut())) {
                                availableApart.add(apart);
                            }
                        }
                    }
                }
            }
        }

        return availableApart;
    }

    public Boolean isAvailable(LocalDate checkIn, LocalDate checkOut, List<Booking> bookings) {
        Boolean isAvailable = false;

        Booking currentBook = new Booking();
        Booking nextBook = new Booking();


        for (int i = 1; i < bookings.size(); i++) {
            currentBook = bookings.get(i - 1);
            nextBook = bookings.get(i);

            if (checkIn.isAfter(nextBook.getCheckOut())) {
                isAvailable = true;
                break;
            }

            if (checkOut.isBefore(currentBook.getCheckIn())) {
                isAvailable = true;
                break;
            }

            if (checkIn.isBefore(currentBook.getCheckIn()) || checkIn.isAfter(currentBook.getCheckOut())) {
                if (checkIn.isBefore(nextBook.getCheckIn()) || checkIn.isAfter(nextBook.getCheckOut())) {
                    if (checkOut.isBefore(currentBook.getCheckIn()) || checkOut.isAfter(currentBook.getCheckOut())) {
                        if (checkOut.isBefore(nextBook.getCheckIn()) || checkOut.isAfter(nextBook.getCheckOut())) {
                            if (checkIn.isBefore(nextBook.getCheckIn()) && checkOut.isBefore(nextBook.getCheckIn())) {
                                if (checkIn.isAfter(nextBook.getCheckOut()) && checkOut.isAfter(nextBook.getCheckOut())) {
                                    isAvailable = true;
                                    break;
                                }
                            }
                        }
                    }
                }
            }


        }

        return isAvailable;
    }

}
