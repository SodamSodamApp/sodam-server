package com.sodam.BookmarkedPlaces.Service;

import com.sodam.BookmarkedPlaces.BookmarkedPlaces;
import com.sodam.BookmarkedPlaces.Repository.BookmarkedRepository;
import com.sodam.common.entity.Place;
import com.sodam.common.entity.UserInfo;
import com.sodam.common.repository.PlaceRepository;
import com.sodam.common.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BookmarkedService {
    private  final BookmarkedRepository bookmarkedRepository;
    private  final PlaceRepository placeRepository;
    private  final UserInfoRepository userInfoRepository;

    public Map<String, Object> getBookmarks(Long userId, Long lastId, int size) {

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

        Long nextLastId = bookmarks.isEmpty() ? null : bookmarks.get(bookmarks.size() - 1).getId();

        Map<String, Object> result = new HashMap<>();
        result.put("content", bookmarks);
        result.put("nextCursor", nextLastId);
        result.put("isLast", bookmarks.size() < size); // true면 마지막 데이터임
        return result;
    }
    public BookmarkedPlaces addBookmarks(Long userId, Long placeId, LocalDateTime localDateTime){
        //1. placeId 로 Place 엔티티 조회(없으면 예외)
        Place place = placeRepository.findById(placeId).orElseThrow(() -> new IllegalArgumentException("해당 장소가 존재하지않습니다: " + placeId));
        UserInfo userInfo = userInfoRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다: " + userId));
        BookmarkedPlaces entity = new BookmarkedPlaces();
        entity.setUserInfo(userInfo);
        entity.setPlace(place);
        entity.setCreatedAt(localDateTime);

        return bookmarkedRepository.save(entity);
    }
}
