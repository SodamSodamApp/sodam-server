package com.sodam.BookmarkedPlaces.DTO;


import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class BookmarkedPlaceRequest {
    private Long userId;
    private Long placeId;

    public BookmarkedPlaceRequest() {
    }
}
