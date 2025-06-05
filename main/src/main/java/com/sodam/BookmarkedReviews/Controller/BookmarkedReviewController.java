package com.sodam.BookmarkedReviews.Controller;

import com.sodam.BookmarkedReviews.Service.BookmarkedReviewService;
import com.sodam.BookmarkedReviews.dto.BookmarkedReviewsDto;
import com.sodam.BookmarkedReviews.dto.BookmarkedReviewsGetDto;
import com.sodam.BookmarkedReviews.dto.BookmarkedReviewsRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/BookmarkedReviews")
@RequiredArgsConstructor
@Tag(name = "Bookmarked Reviews", description = "찜(하트)한 리뷰 API")
public class BookmarkedReviewController {

    private final BookmarkedReviewService bookmarkedReviewService;

    @Operation(summary = "사용자의 찜한 리뷰 목록 조회")
    @GetMapping("/{user_id}")
    public ResponseEntity<List<BookmarkedReviewsGetDto>> getReviewBookmarks(
            @PathVariable("user_id") Long userId,
            @RequestParam(name = "lastId", required = false) Long lastId,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        List<BookmarkedReviewsGetDto> list = bookmarkedReviewService.getBookmarkedReviews(userId, lastId, size);
        return ResponseEntity.ok(list);
    }


    @Operation(summary = "리뷰 찜(하트) 추가")
    @PostMapping("/addReviewBookmark")
    public ResponseEntity<BookmarkedReviewsDto> addBookmark(@RequestBody BookmarkedReviewsRequest request) {
        BookmarkedReviewsDto response = bookmarkedReviewService.addBookmarkReview(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "리뷰 찜(하트) 삭제")
    @PatchMapping("/deleteReviewBookmark/{id}")
    public ResponseEntity<Map<String, Object>> deleteReviewBookmark(@PathVariable("id") Long id) {
        BookmarkedReviewsDto res = bookmarkedReviewService.deleteBookmarkReview(id);
        return ResponseEntity.ok(Map.of("status", "success","data",res));
    }
}
