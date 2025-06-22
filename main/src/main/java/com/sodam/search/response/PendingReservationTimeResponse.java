package com.sodam.search.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class PendingReservationTimeResponse {
    private List<LocalDateTime> availableTimes;
}
