package com.sodam.BookmarkedPlaces.Repository;

import com.sodam.BookmarkedPlaces.BookmarkedPlaces;
import com.sodam.common.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface BookmarkedRepository extends JpaRepository<BookmarkedPlaces,Long> {

    /**
     * 최초 호출: 최신순 size개
     * findByUserInfoOrderByIdDesc : userInfo 조건 + id 내림차순 정렬
     * Pageable pageable : size(페이지 크기)만큼 가져오기
     */
    List<BookmarkedPlaces> findByUserInfoOrderByIdDesc(UserInfo userInfo, Pageable pageable);

    /**
     * 커서 기반 페이징: 마지막 id보다 작은 것 중 최신순 size개
     * findByUserInfoAndIdLessThanOrderByIdDesc :
     *   - userInfo 조건
     *   - id < lastId (이전 데이터)
     *   - id 내림차순(최신순) 정렬
     *   - Pageable pageable : size만큼만 가져오기
     */
    List<BookmarkedPlaces> findByUserInfoAndIdLessThanOrderByIdDesc(UserInfo userInfo, Long lastId, Pageable pageable);


}
