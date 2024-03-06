package com.anasajimuhammed.newurl.services.impl;

import com.anasajimuhammed.newurl.models.ClickEvents;
import com.anasajimuhammed.newurl.models.URLModel;
import com.anasajimuhammed.newurl.repository.ClickEventsRepository;
import com.anasajimuhammed.newurl.repository.UrlStoreSQLRepository;
import com.anasajimuhammed.newurl.services.URLOperationsService;
import com.anasajimuhammed.newurl.utils.UrlShortenUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class URLOperationsServiceImpl implements URLOperationsService {

    private UrlStoreSQLRepository urlStoreSQLRepository;
    private ClickEventsRepository clickEventsRepository;
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
        urlStoreSQLRepository.delete(urlData);

        return addNewURL(urlData);
    }

    @Override
    public void deleteURL(URLModel urlData) {
        urlStoreSQLRepository.delete(urlData);

    }

    public URLModel getURL(String url) {

        return urlStoreSQLRepository.findByUrlHash(url);
    }

    @Override
    public List<URLModel> getURLs() {
        return urlStoreSQLRepository.findAll();
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


}
