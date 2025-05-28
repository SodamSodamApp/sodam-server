package com.sodam.BookmarkedPlaces.Repository;

import com.sodam.BookmarkedPlaces.BookmarkedPlaces;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookmarkedRepository extends JpaRepository<BookmarkedPlaces,Long> {

    /*최초 호출: 최신순 size개
    findTop10 : 상위 10개만
    ByUserId : userId 조건
    OrderByIdDesc : id 내림차순 */
    List<BookmarkedPlaces> findTop10ByUserIdOrderByIdDesc(Long userId);

    /*
    findTop10 : 최대 10개까지 조회
    ByUserIdAndIdLessThan : userId가 주어진 값과 같고, id가 lastId보다 작은 것만
    OrderByIdDesc : id를 기준으로 내림차순(=최신순) 정렬
    커서 기반: 마지막 id보다 작은 것 중 최신순 size개 */
    List<BookmarkedPlaces> findTop10ByUserIdAndIdLessThanOrderByIdDesc(Long userId, Long lastId);

    // size를 동적으로 받고 싶으면:
    List<BookmarkedPlaces> findTopNByUserIdOrderByIdDesc(Long userId, int n);
    List<BookmarkedPlaces> findTopNByUserIdAndIdLessThanOrderByIdDesc(Long userId, Long id, int n);
}
