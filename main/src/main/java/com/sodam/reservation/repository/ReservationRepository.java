package com.sodam.reservation.repository;

import com.sodam.common.entity.Place;
import com.sodam.reservation.entity.Reservation;
import com.sodam.reservation.entity.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String> {
    List<Reservation> findByPlaceAndReservationStatusAndAvailableTimeGreaterThanEqual(
            Place place, 
            ReservationStatus status, 
            LocalDateTime now
    );
} 