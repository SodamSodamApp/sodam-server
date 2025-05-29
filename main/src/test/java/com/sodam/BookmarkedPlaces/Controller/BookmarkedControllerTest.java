package com.sodam.BookmarkedPlaces.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sodam.BookmarkedPlaces.dto.BookmarkedPlacesRequest;
import com.sodam.common.entity.Place;
import com.sodam.common.entity.UserInfo;
import com.sodam.common.repository.PlaceRepository;
import com.sodam.common.repository.UserInfoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookmarkedControllerTest {
 /*   @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private UserInfo testUser;
    private Place testPlace;

    @BeforeEach
    void setUp() {
        testUser = userInfoRepository.save(UserInfo.builder().nickname("테스트유저").build()
        );

        testPlace = placeRepository.save(Place.builder().name("테스트장소").address("테스트 주소").build());



    }

    @Test
    @DisplayName("찜 추가 API")
    void addBookmarkTest() throws Exception{

        BookmarkedPlacesRequest request = new BookmarkedPlacesRequest();
        request.setUserId(testUser.getId());
        request.setPlaceId(testPlace.getId());

        mockMvc.perform(post("/api/BookmarkedPlaces/addBookmark")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.data.userId").value(testUser.getId()))
                .andExpect(jsonPath("$.data.placeId").value(testPlace.getId()));

    }*/


}