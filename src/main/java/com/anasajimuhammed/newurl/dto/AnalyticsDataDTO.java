package com.anasajimuhammed.newurl.dto;

import com.anasajimuhammed.newurl.models.AggregatedClickEvents;
import com.anasajimuhammed.newurl.models.ClickEvents;
import com.anasajimuhammed.newurl.models.URLModel;
import io.swagger.v3.oas.models.info.License;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnalyticsDataDTO {
    private Long totalCount;

    URLModel urlDetails;

//    List<ClickEvents> clickEventsList;

    LocalDateTime startDate;

    LocalDateTime endDate;

    Long Id;

    String baseURL;

    List<AggregatedClickEvents> linkAnalyticsData;

}
