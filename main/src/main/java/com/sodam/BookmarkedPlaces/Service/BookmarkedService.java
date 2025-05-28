package com.sodam.BookmarkedPlaces.Service;

import com.sodam.BookmarkedPlaces.BookmarkedPlaces;
import com.sodam.BookmarkedPlaces.Repository.BookmarkedRepository;
import com.sodam.BookmarkedPlaces.dto.BookmarkedPlacesDto;
import com.sodam.common.entity.Place;
import com.sodam.common.entity.UserInfo;
import com.sodam.common.repository.PlaceRepository;
import com.sodam.common.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookmarkedService {
    private  final BookmarkedRepository bookmarkedRepository;
    private  final PlaceRepository placeRepository;
    private  final UserInfoRepository userInfoRepository;

    @Transactional
    public List<BookmarkedPlacesDto> getBookmarks(Long userId, Long lastId, int size) {
        // userId로 UserInfo 엔티티를 조회
        UserInfo userInfo = userInfoRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 userId"));

        // Pageable 생성 (0-based page, size개만큼)
        Pageable pageable = PageRequest.of(0, size);

        // 최초 호출이면 최신순부터 size개
        List<BookmarkedPlaces> bookmarks;
        if (lastId == null || lastId == 0) {
            bookmarks = bookmarkedRepository.findByUserInfoOrderByIdDesc(userInfo, pageable);
        } else {
            bookmarks = bookmarkedRepository.findByUserInfoAndIdLessThanOrderByIdDesc(userInfo, lastId, pageable);
        }

        // BookmarkedPlaces 리스트 -> BookmarkedPlaceDto 리스트로 변환해서 반환
        return bookmarks.stream()
                .map(BookmarkedPlacesDto::fromEntity)
                .toList();
    }
    @Transactional
    public BookmarkedPlacesDto addBookmarks(Long userId, Long placeId, LocalDateTime localDateTime) {
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new IllegalArgumentException("해당 장소가 존재하지 않습니다: " + placeId));
        UserInfo userInfo = userInfoRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다: " + userId));

        BookmarkedPlaces entity = BookmarkedPlaces.builder()
                .userInfo(userInfo)
                .place(place)
                .createdAt(localDateTime)
                .build();

        BookmarkedPlaces saved = bookmarkedRepository.save(entity);
        return BookmarkedPlacesDto.fromEntity(saved); // 엔티티 → DTO로 반환
    }
}
