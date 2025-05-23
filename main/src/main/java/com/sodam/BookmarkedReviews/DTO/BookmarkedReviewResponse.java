package com.sodam.BookmarkedReviews.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BookmarkedReviewResponse {
    private Long id;
    private Long reviewId;
    private String reviewSummary;
    private String writer;
    private LocalDateTime bookmarkedAt;
}