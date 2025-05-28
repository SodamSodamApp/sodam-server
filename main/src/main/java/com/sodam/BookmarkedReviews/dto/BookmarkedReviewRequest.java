package com.sodam.BookmarkedReviews.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookmarkedReviewRequest {
    private Long userId;
    private Long reviewId;
}