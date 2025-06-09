package com.sodam.search.service;

import com.sodam.common.entity.Place;
import com.sodam.common.repository.PlaceRepository;
import com.sodam.reservation.entity.Reservation;
import com.sodam.reservation.entity.ReservationStatus;
import com.sodam.reservation.repository.ReservationRepository;
import com.sodam.review.repository.ReviewRepository;
import com.sodam.search.response.PendingReservationTimeResponse;
import com.sodam.search.response.ReviewPhotoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SearchService {

    private final PlaceRepository placeRepository;
    private final ReviewRepository reviewRepository;
    private final ReservationRepository reservationRepository;

    public ReviewPhotoResponse getReviewPhotoByPlaceId(Long placeId) {
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new RuntimeException("Place not found with id: " + placeId));

        List<String> photoUrls = reviewRepository.findFirstReviewPhotoUrlByPlace(place, PageRequest.of(0, 1));
        
        if (photoUrls.isEmpty()) {
            return ReviewPhotoResponse.builder().photoUrl(null).build(); // 사진이 없을 경우 null 또는 기본 이미지 URL
        }
        return ReviewPhotoResponse.builder().photoUrl(photoUrls.get(0)).build();
    }

    public PendingReservationTimeResponse getPendingReservationTimesByPlaceId(Long placeId) {
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new RuntimeException("Place not found with id: " + placeId));

        List<Reservation> reservations = reservationRepository
                .findByPlaceAndReservationStatusAndAvailableTimeGreaterThanEqual(
                        place, 
                        ReservationStatus.PENDING, 
                        LocalDateTime.now()
                );

        List<LocalDateTime> availableTimes = reservations.stream()
                .map(Reservation::getAvailableTime)
                .collect(Collectors.toList());

        return PendingReservationTimeResponse.builder().availableTimes(availableTimes).build();
    }
} 