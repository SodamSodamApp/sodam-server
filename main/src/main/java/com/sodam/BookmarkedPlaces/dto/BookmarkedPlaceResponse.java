package com.sodam.BookmarkedPlaces.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookmarkedPlaceResponse {
    private Long id;
    private Long placeId;
    private String placeName;
    private String address;
    private LocalDateTime createdAt;

    public BookmarkedPlaceResponse(Long id, Long placeId, String placeName, String address, LocalDateTime createdAt) {
        this.id = id;
        this.placeId = placeId;
        this.placeName = placeName;
        this.address = address;
        this.createdAt = createdAt;
    }
}