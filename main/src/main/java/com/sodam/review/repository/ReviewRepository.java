package com.sodam.review.repository;

import com.sodam.common.entity.Place;
import com.sodam.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByPlaceAndIdGreaterThanOrderByIdAsc(Place place, long cursor, Pageable pageable);

    @Query("SELECT rp.photoUrl FROM Review r JOIN r.place p JOIN ReviewPhoto rp ON rp.review = r WHERE p = :place ORDER BY r.createdAt DESC, rp.id ASC")
    List<String> findFirstReviewPhotoUrlByPlace(@Param("place") Place place, Pageable pageable);
}
