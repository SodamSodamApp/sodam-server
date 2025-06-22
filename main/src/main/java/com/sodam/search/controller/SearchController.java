package com.sodam.search.controller;
import com.sodam.search.response.PendingReservationTimeResponse;
import com.sodam.search.response.ReviewPhotoResponse;
import com.sodam.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    // 장소 리뷰 사진 url 조회
    @GetMapping("/places/{placeId}/review-photo")
    public ResponseEntity<ReviewPhotoResponse> getReviewPhotoByPlaceId(@PathVariable Long placeId) {
        ReviewPhotoResponse response = searchService.getReviewPhotoByPlaceId(placeId);
        return ResponseEntity.ok(response);
    }

    // 장소 예약가능한 시간 조회
    @GetMapping("/places/{placeId}/pending-reservations")
    public ResponseEntity<PendingReservationTimeResponse> getPendingReservationTimesByPlaceId(@PathVariable Long placeId) {
        PendingReservationTimeResponse response = searchService.getPendingReservationTimesByPlaceId(placeId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/places")
    public ResponseEntity<?> getPlaces(@RequestParam("placeId") String placeId) {
        // TODO: 기존 장소 검색 로직 구현
        return ResponseEntity.ok("장소 검색 로직 구현 필요");
    }
}
