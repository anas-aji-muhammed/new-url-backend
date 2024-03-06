package com.anasajimuhammed.newurl.dto;

import com.anasajimuhammed.newurl.models.ClickEvents;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnalyticsDataDTO {
    private Long totalCount;

    List<ClickEvents> clickEventsList;

    LocalDateTime startDate;

    LocalDateTime endDate;

    Long Id;
}
