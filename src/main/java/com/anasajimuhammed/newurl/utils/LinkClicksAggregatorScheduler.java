//package com.anasajimuhammed.newurl.utils;
//
//import com.anasajimuhammed.newurl.services.URLOperationsService;
//import com.anasajimuhammed.newurl.services.impl.URLOperationsServiceImpl;
//import lombok.RequiredArgsConstructor;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class LinkClicksAggregatorScheduler {
//
//    private final URLOperationsService urlOperationsService;
//
//    @Scheduled(fixedRate = 60000)
//    public void runLinkAggregator() {
//        urlOperationsService.aggregateUrlData();
//    }
//
//}
