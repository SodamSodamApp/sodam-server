package com.sodam.BookmarkedPlaces.Controller;


import com.sodam.BookmarkedPlaces.BookmarkedPlaces;
import com.sodam.BookmarkedPlaces.Service.BookmarkedService;
import com.sodam.BookmarkedPlaces.DTO.BookmarkedPlaceRequest;
import com.sodam.BookmarkedPlaces.DTO.BookmarkedPlaceResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/BookmarkedPlaces")
@RequiredArgsConstructor
@Tag(name = "Bookmarked Places", description = "찜(하트)한 가게 API")
public class BookmarkedController {

    private  final BookmarkedService bookmarkedService;

    @Operation(summary = "사용자의 찜한 가게 목록 조회 (커서 기반 무한스크롤)")
    @GetMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> getBookmarks(
            @PathVariable Long userId,
            @RequestParam(required = false) Long lastId,   // 처음 호출시 null
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(bookmarkedService.getBookmarks(userId, lastId, size));
    }


    @Operation(summary = "찜(하트) 추가")
    @PostMapping("/addBoomark")
    public ResponseEntity<Map<String, Object>> addBookmark(
            @RequestBody BookmarkedPlaceRequest request
    ) {
        BookmarkedPlaces entity = bookmarkedService.addBookmarks(
                request.getUserId(),
                request.getPlaceId(),
                LocalDateTime.now()
        );
        BookmarkedPlaceResponse res = new BookmarkedPlaceResponse(
                entity.getUserInfo().getId(),
                entity.getPlace().getId(),
                entity.getPlace().getName(),
                entity.getPlace().getAddress(),
                entity.getCreatedAt()
        );
        return ResponseEntity.ok(Map.of("status", "success", "data", res));
    }

    @Operation(summary = "찜(하트) 삭제")
    @PatchMapping("/deleteBookmark")
    public ResponseEntity<Map<String, String>> deleteBookmark(
            @RequestBody BookmarkedPlaceRequest request
    ) {
        return ResponseEntity.ok(Map.of("status", "success", "message", "Bookmark deleted"));
    }
}
