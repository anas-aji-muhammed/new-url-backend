package com.anasajimuhammed.newurl.controllers;
import com.anasajimuhammed.newurl.dto.AnalyticsDataDTO;
import com.anasajimuhammed.newurl.dto.URLDataResponseModel;
import com.anasajimuhammed.newurl.models.URLModel;
import com.anasajimuhammed.newurl.repository.UrlStoreSQLRepository;
import com.anasajimuhammed.newurl.services.impl.URLOperationsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class UrlShortenController {
    private URLOperationsServiceImpl urlOperationsService;

    private Environment env;


    @PostMapping("shorten-url")
    public URLModel shortenNewURL(@RequestBody URLModel urlData){
//        System.out.print("getOriginalURL"+urlData.getOriginalURL());
        return urlOperationsService.addNewURL(urlData);
    }

    @GetMapping("getUrls")
    public URLDataResponseModel getUrls(){
        URLDataResponseModel urlDataResponse = new URLDataResponseModel();
        urlDataResponse.setUrlData(urlOperationsService.getURLs());
        urlDataResponse.setBaseURL(env.getProperty("app.baseUrl"));
        return urlDataResponse;
    }

    @GetMapping("analytics/{urlId}")
    public AnalyticsDataDTO getAnalytics(
            @PathVariable Long urlId,
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        AnalyticsDataDTO analyticsDataDTO = new AnalyticsDataDTO();
        analyticsDataDTO.setClickEventsList(urlOperationsService.getUrlAnalytics(urlId, startDate, endDate));
        analyticsDataDTO.setTotalCount((long) analyticsDataDTO.getClickEventsList().size());
        return analyticsDataDTO;
    }


 }
