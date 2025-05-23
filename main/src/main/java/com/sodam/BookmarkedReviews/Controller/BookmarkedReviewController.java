package com.sodam.BookmarkedReviews.Controller;

import com.sodam.BookmarkedReviews.DTO.BookmarkedReviewRequest;
import com.sodam.BookmarkedReviews.DTO.BookmarkedReviewResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/BookmarkedReviews")
@Tag(name = "Bookmarked Reviews", description = "찜(하트)한 리뷰 API")
public class BookmarkedReviewController {

    @Operation(summary = "사용자의 찜한 리뷰 목록 조회")
    @GetMapping("/{user_id}")
    public ResponseEntity<Map<String, String>> getReviewBookmarks(@PathVariable Long user_id) {
        return ResponseEntity.ok(
                Map.of("message", "이건 리뷰 찜 목록 조회 API입니다.")
        );
    }

    @Operation(summary = "리뷰 찜(하트) 추가")
    @PostMapping("/addReviewBookmark")
    public ResponseEntity<Map<String, String>> addReviewBookmark(@RequestBody BookmarkedReviewRequest request) {
        return ResponseEntity.ok(
                Map.of("message", "이건 리뷰 찜 추가 API입니다.")
        );
    }

    @Operation(summary = "리뷰 찜(하트) 삭제")
    @PatchMapping("/deleteReviewBookmark")
    public ResponseEntity<Map<String, String>> deleteReviewBookmark(@RequestBody BookmarkedReviewRequest request) {
        return ResponseEntity.ok(
                Map.of("message", "이건 리뷰 찜 삭제 API입니다.")
        );
    }
}
