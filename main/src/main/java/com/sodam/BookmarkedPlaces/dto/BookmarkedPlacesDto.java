package com.sodam.BookmarkedPlaces.dto;

import com.sodam.BookmarkedPlaces.BookmarkedPlaces;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookmarkedPlacesDto {
    private Long id;
    private Long userId;        // 유저 ID 필드 추가
    private Long placeId;
    private String placeName;
    private String address;
    private LocalDateTime createdAt;

    public static BookmarkedPlacesDto fromEntity(BookmarkedPlaces entity) {
        return BookmarkedPlacesDto.builder()
                .id(entity.getId())
                .userId(entity.getUserInfo().getId())   // 유저 ID 추출
                .placeId(entity.getPlace().getId())
                .placeName(entity.getPlace().getName())
                .address(entity.getPlace().getAddress())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
