package com.sodam.BookmarkedReviews.dto;

import com.sodam.BookmarkedReviews.BookmarkedReviews;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookmarkedReviewsDto {
    private Long id;
    private Long userId;
    private Long reviewId;
    private LocalDateTime createdAt;

    public static BookmarkedReviewsDto fromEntity(BookmarkedReviews entity) {
        return BookmarkedReviewsDto.builder()
                .id(entity.getId())
                .userId(entity.getUserInfo().getId())
                .reviewId(entity.getReview().getId())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
