package com.sodam.BookmarkedPlaces.dto;


import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class BookmarkedPlaceRequest {
    private Long userId;
    private Long placeId;

    public BookmarkedPlaceRequest() {
    }
}
