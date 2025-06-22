package com.sodam.BookmarkedPlaces.dto;


import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class BookmarkedPlacesRequest {
    private Long userId;
    private Long placeId;

    public BookmarkedPlacesRequest() {
    }
}
