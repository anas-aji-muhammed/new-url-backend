package com.anasajimuhammed.newurl.services.impl;

import com.anasajimuhammed.newurl.models.AggregatedClickEvents;
import com.anasajimuhammed.newurl.models.ClickEvents;
import com.anasajimuhammed.newurl.models.URLModel;
import com.anasajimuhammed.newurl.repository.AggregateClickEventsRepository;
import com.anasajimuhammed.newurl.repository.ClickEventsRepository;
import com.anasajimuhammed.newurl.repository.UrlStoreSQLRepository;
import com.anasajimuhammed.newurl.services.URLOperationsService;
import com.anasajimuhammed.newurl.utils.UrlShortenUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class URLOperationsServiceImpl implements URLOperationsService {

    private UrlStoreSQLRepository urlStoreSQLRepository;
    private ClickEventsRepository clickEventsRepository;
    private AggregateClickEventsRepository aggregateClickEventsRepository;
    private static int SHORT_URL_CHAR_SIZE=7;


    @Override
    public String shortenURL(String urlData) {
        String hash = UrlShortenUtil.md5UrlShorten(urlData);
        int numberOfCharsInHash = hash.length();
        int counter = 0;
//        return  hash.substring(0, counter + SHORT_URL_CHAR_SIZE);
        while (counter < numberOfCharsInHash - SHORT_URL_CHAR_SIZE) {
            if (!urlStoreSQLRepository.existsByUrlHash(hash.substring(counter, counter + SHORT_URL_CHAR_SIZE))) {
                return hash.substring(counter, counter + SHORT_URL_CHAR_SIZE);
            }
            counter++;
        }
        return hash;
    }

    @Override
    public URLModel addNewURL(URLModel urlData) {
        String getURLHash = shortenURL(urlData.getOriginalURL());
        urlData.setUrlHash(getURLHash);
        urlData.setIsActive(true);
        urlData.setCount(0L);
        return urlStoreSQLRepository.save(urlData);
    }

    @Override
    public URLModel updateURL(URLModel urlData) {

        return urlStoreSQLRepository.save(urlData);
    }

    @Override
    public void deleteURL(URLModel urlData) {
        urlStoreSQLRepository.delete(urlData);

    }

    @Override
    public List<URLModel> getURLs() {
        return urlStoreSQLRepository.findAll();
    }

    public URLModel getURL(String url) {

        return urlStoreSQLRepository.findByUrlHash(url);
    }

    @Override
    public void urlClickEventAdd(ClickEvents clickEvent) {
        clickEventsRepository.save(clickEvent);
    }

    @Override
    public List<ClickEvents> getUrlAnalytics(Long id, LocalDateTime start, LocalDateTime end) {
        if(start!=null){
            return clickEventsRepository.findClicksByUrlIdAndDateRange(id, start, end);

        }

        else{
            return clickEventsRepository.findAllByUrlId(id);

        }
    }
    public List<AggregatedClickEvents> getAggregatedUrlAnalytics(Long id) {
        Optional<List<AggregatedClickEvents>> aggregatedClickEventsList = aggregateClickEventsRepository.findAllByUrlId(id);
        return aggregatedClickEventsList.orElseGet(ArrayList::new);
    }


    public void aggregateUrlData() {
        List<ClickEvents> allClickEvents = clickEventsRepository.findAll();
        Map<LocalDate, Map<Long, List<ClickEvents>>> groupedByDateAndUrlId =
                allClickEvents.stream().collect(Collectors.groupingBy(
                        event -> event.getClickedAt().toLocalDate(),
                        Collectors.groupingBy(ClickEvents::getUrlId)
                ));

        List<AggregatedClickEvents> aggregatedClickEventsList = new ArrayList<>();

        groupedByDateAndUrlId.forEach((date, urlIdMap) -> {
            urlIdMap.forEach((urlId, events) -> {
                AggregatedClickEvents aggregatedClickEvent = aggregateClickEventsRepository
                        .findByUrlIdAndDate(urlId, date)
                        .orElse(AggregatedClickEvents.builder()
                                .urlId(urlId)
                                .date(date)
                                .count(0L)
                                .linkClicks(0L)
                                .qrScans(0L)
                                .build());

                events.forEach(event -> {
                    aggregatedClickEvent.setCount(aggregatedClickEvent.getCount() + 1);
                    if (event.getLinkSource().equals("qr")) {
                        aggregatedClickEvent.setQrScans(aggregatedClickEvent.getQrScans() + 1);
                    } else {
                        aggregatedClickEvent.setLinkClicks(aggregatedClickEvent.getLinkClicks() + 1);
                    }
                });
                aggregatedClickEventsList.add(aggregatedClickEvent);
            });
        });

        aggregateClickEventsRepository.saveAll(aggregatedClickEventsList);
    }



}
