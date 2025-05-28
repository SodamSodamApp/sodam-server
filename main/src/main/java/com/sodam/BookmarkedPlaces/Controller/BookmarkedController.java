package com.sodam.BookmarkedPlaces.Controller;

import com.sodam.BookmarkedPlaces.dto.BookmarkedPlacesDto;
import com.sodam.BookmarkedPlaces.dto.BookmarkedPlacesRequest;
import com.sodam.BookmarkedPlaces.Service.BookmarkedService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/BookmarkedPlaces")
@RequiredArgsConstructor
@Tag(name = "Bookmarked Places", description = "찜(하트)한 가게 API")
public class BookmarkedController {

    private final BookmarkedService bookmarkedService;

    @Operation(summary = "사용자의 찜한 가게 목록 조회 (커서 기반 무한스크롤)")
    @GetMapping("/{userId}")
    public ResponseEntity<List<BookmarkedPlacesDto>> getBookmarks(
            @PathVariable Long userId,
            @RequestParam(required = false) Long lastId,   // 처음 호출시 null
            @RequestParam(defaultValue = "10") int size
    ) {
        List<BookmarkedPlacesDto> bookmarks = bookmarkedService.getBookmarks(userId, lastId, size);
        return ResponseEntity.ok(bookmarks);
    }

    @Operation(summary = "찜(하트) 추가")
    @PostMapping("/addBookmark")
    public ResponseEntity<Map<String, Object>> addBookmark(
            @RequestBody BookmarkedPlacesRequest request
    ) {
        BookmarkedPlacesDto res = bookmarkedService.addBookmarks(
                request.getUserId(),
                request.getPlaceId(),
                LocalDateTime.now()
        );
        return ResponseEntity.ok(Map.of("status", "success", "data", res));
    }

    @Operation(summary = "찜(하트) 삭제")
    @PatchMapping("/deleteBookmark")
    public ResponseEntity<Map<String, String>> deleteBookmark(
            @RequestBody BookmarkedPlacesRequest request
    ) {
        // 삭제 로직 구현 필요 (void 또는 boolean 반환 추천)
        return ResponseEntity.ok(Map.of("status", "success", "message", "Bookmark deleted"));
    }
}
