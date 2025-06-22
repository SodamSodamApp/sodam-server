package com.sodam.BookmarkedReviews;

import com.sodam.common.entity.Place;
import com.sodam.common.entity.UserInfo;
import com.sodam.review.entity.Review;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "bookmarked_reviews")
public class BookmarkedReviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private UserInfo userInfo;


    @JoinColumn(name ="review_id")
    @ManyToOne
    private Review review;

    @CreationTimestamp
    @Column(updatable = true)
    private LocalDateTime createdAt;
}
