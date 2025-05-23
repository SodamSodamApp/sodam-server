package com.sodam.BookmarkedPlaces.Controller;


import com.sodam.BookmarkedPlaces.dto.BookmarkedPlaceRequest;
import com.sodam.BookmarkedPlaces.dto.BookmarkedPlaceResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/BookmarkedPlaces")
@Tag(name = "Bookmarked Places", description = "찜(하트)한 가게 API")
public class BookmarkedController {

    @Operation(summary = "사용자의 찜한 가게 목록 조회")
    @GetMapping("/{user_id}")
    public ResponseEntity<Map<String, Object>> getBookmarks(@PathVariable Long user_id) {
        // 임시 데이터
        List<BookmarkedPlaceResponse> list = Arrays.asList(
                new BookmarkedPlaceResponse(1L, 101L, "카페유림", "서울시 강남구 ...", LocalDateTime.now().minusDays(2)),
                new BookmarkedPlaceResponse(2L, 102L, "버거킹", "서울시 서초구 ...", LocalDateTime.now().minusDays(5))
        );
        return ResponseEntity.ok(Map.of("status", "success", "data", list));
    }

    @Operation(summary = "찜(하트) 추가")
    @PostMapping("/addBoomark")
    public ResponseEntity<Map<String, Object>> addBookmark(
            @RequestBody BookmarkedPlaceRequest request
    ) {
        // 임시 데이터
        BookmarkedPlaceResponse res = new BookmarkedPlaceResponse(
                3L, request.getPlaceId(), "임시가게", "서울시 송파구 ...", LocalDateTime.now()
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
