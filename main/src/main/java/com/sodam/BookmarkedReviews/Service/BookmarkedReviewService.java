package com.sodam.BookmarkedReviews.Service;

import com.sodam.BookmarkedReviews.BookmarkedReviews;
import com.sodam.BookmarkedReviews.Repository.BookmarkedReviewRepository;
import com.sodam.BookmarkedReviews.dto.BookmarkedReviewsDto;
import com.sodam.BookmarkedReviews.dto.BookmarkedReviewsGetDto;
import com.sodam.BookmarkedReviews.dto.BookmarkedReviewsRequest;
import com.sodam.common.entity.UserInfo;
import com.sodam.common.repository.PlaceRepository;
import com.sodam.common.repository.UserInfoRepository;
import com.sodam.common.service.FileStorageService;
import com.sodam.review.dto.ReviewDto;
import com.sodam.review.entity.Review;
import com.sodam.review.repository.ReviewPhotoRepository;
import com.sodam.review.repository.ReviewRepository;
import com.sodam.review.repository.ReviewTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookmarkedReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewTagRepository reviewTagRepository;
    private final PlaceRepository placeRepository;
    private final UserInfoRepository userInfoRepository;
    private final ReviewPhotoRepository reviewPhotoRepository;
    private final FileStorageService fileStorageService;
    private final BookmarkedReviewRepository bookmarkedReviewRepository;

    @Transactional
    public BookmarkedReviewsDto addBookmarkReview(BookmarkedReviewsRequest request) {
        UserInfo userInfo = userInfoRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Review review = reviewRepository.findById(request.getReviewId()).orElseThrow(() -> new RuntimeException("review not found"));


        // 3. 북마크 저장
        BookmarkedReviews bookmarkedReviews = BookmarkedReviews.builder()
                .userInfo(userInfo)
                .review(review)
                .build();

        BookmarkedReviews savedBookmarkedReviews = bookmarkedReviewRepository.save(bookmarkedReviews);



        return BookmarkedReviewsDto.fromEntity(savedBookmarkedReviews);
    }

    @Transactional
    public BookmarkedReviewsDto  deleteBookmarkReview(Long id) {
        BookmarkedReviews bookmarkedReviews = bookmarkedReviewRepository.findById(id).orElseThrow(() -> new RuntimeException("id not found"));

        bookmarkedReviewRepository.delete(bookmarkedReviews);

        return BookmarkedReviewsDto.fromEntity(bookmarkedReviews);
    }

    @Transactional
    public List<BookmarkedReviewsGetDto> getBookmarkedReviews(Long userId, Long lastId, int size) {
        // 1. userId로 UserInfo 엔티티 조회
        UserInfo userInfo = userInfoRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 userId"));

        // 2. Pageable 생성
        Pageable pageable = PageRequest.of(0, size);

        // 3. 북마크 목록 조회 (최초 or 스크롤)
        List<BookmarkedReviews> bookmarks;
        if (lastId == null || lastId == 0) {
            // 최초 호출: 최신순 size개
            bookmarks = bookmarkedReviewRepository.findByUserInfoOrderByIdDesc(userInfo, pageable);
        } else {
            // 커서 기반(스크롤): lastId보다 작은 id 최신순 size개
            bookmarks = bookmarkedReviewRepository.findByUserInfoAndIdLessThanOrderByIdDesc(userInfo, lastId, pageable);
        }

        // 4. BookmarkedReviews 리스트 -> BookmarkedReviewsGetDto 리스트로 변환
        return bookmarks.stream()
                .map(bookmark -> {
                    var review = bookmark.getReview();
                    var reviewTags = reviewTagRepository.findByReview(review);
                    var reviewPhotos = reviewPhotoRepository.findByReview(review);
                    var reviewDto = ReviewDto.fromEntity(review, reviewTags, reviewPhotos);
                    return BookmarkedReviewsGetDto.fromEntity(bookmark, reviewDto);
                })
                .toList();
    }

}
