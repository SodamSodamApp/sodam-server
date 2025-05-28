package com.sodam.BookmarkedPlaces.Service;

import com.sodam.BookmarkedPlaces.BookmarkedPlaces;
import com.sodam.BookmarkedPlaces.Repository.BookmarkedRepository;
import com.sodam.common.entity.Place;
import com.sodam.common.entity.UserInfo;
import com.sodam.common.repository.PlaceRepository;
import com.sodam.common.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
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
        List<BookmarkedPlaces> bookmarks;

        // 최초 호출이면 최신순부터 size개
        if (lastId == null || lastId == 0) {
            bookmarks = bookmarkedRepository.findTopNByUserIdOrderByIdDesc(userId, size);
        } else {
            // 마지막 id보다 작은(더 예전) 것 중 size개
            bookmarks = bookmarkedRepository.findTopNByUserIdAndIdLessThanOrderByIdDesc(userId, lastId, size);
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
