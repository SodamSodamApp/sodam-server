package com.sodam.reservation.entity;

import com.sodam.common.entity.Place;
import com.sodam.common.entity.UserInfo;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @Column(name = "id", length = 50)
    private String id; // UUID 등을 사용할 수 있으므로 String 타입

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserInfo userInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id", nullable = false)
    private Place place;

    @Column(name = "available_time", nullable = false)
    private LocalDateTime availableTime;

    @Column(name = "reservation_status", length = 20)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ReservationStatus reservationStatus = ReservationStatus.PENDING;

    @Column(name = "confirmed_at")
    private LocalDateTime confirmedAt;

    @Column(name = "cancelled_at")
    private LocalDateTime cancelledAt;
} 