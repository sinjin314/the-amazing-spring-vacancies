/*
 * Copyright (c) 2021.
 * Abdelhadi Hasnaoui <abdelhadi.hasnaoui@protonmail.com>
 * sinjin.tech
 */

package com.apivacancies.lab.location.repository;

import com.apivacancies.lab.location.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query(value = "select b.id, b.check_in, b.check_out from booking b join apartment_bookings ab on b.id = ab.bookings_id where ab.apartment_id = :appart_id ", nativeQuery = true)
    List<Booking> findBookingByAppart(Long appart_id);

    @Query(value = "select b.id, b.check_in, b.check_out from booking b join apartment_bookings ab on b.id = ab.bookings_id where ab.apartment_id = :appart_id order by date(b.check_in)", nativeQuery = true)
    List<Booking> findBookingsByAppartInCheckInOrder(Long appart_id);
}
