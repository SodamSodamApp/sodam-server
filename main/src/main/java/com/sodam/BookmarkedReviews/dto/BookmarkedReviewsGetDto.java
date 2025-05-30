package com.sodam.BookmarkedReviews.dto;

import com.sodam.review.dto.ReviewDto;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookmarkedReviewsGetDto {
    private  Long id;
    private Long userId;
    private ReviewDto review;
    private LocalDateTime createdAt;

    public static BookmarkedReviewsGetDto fromEntity(com.sodam.BookmarkedReviews.BookmarkedReviews entity, ReviewDto reviewDto) {
        return BookmarkedReviewsGetDto.builder()
                .id(entity.getId())
                .userId(entity.getUserInfo().getId())
                .review(reviewDto) // 이미 변환된 ReviewDto만 넣어주면 됨
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
