package com.anasajimuhammed.newurl.services.impl;

import com.anasajimuhammed.newurl.models.URLModel;
import com.anasajimuhammed.newurl.repository.UrlStoreSQLRepository;
import com.anasajimuhammed.newurl.services.URLOperationsService;
import com.anasajimuhammed.newurl.utils.UrlShortenUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class URLOperationsServiceImpl implements URLOperationsService {

    private UrlStoreSQLRepository urlStoreSQLRepository;
    private static int SHORT_URL_CHAR_SIZE=7;



    @Override
    public String shortenURL(String urlData) {
        String hash = UrlShortenUtil.md5UrlShorten(urlData);
        int numberOfCharsInHash = hash.length();
        int counter = 0;
        return  hash.substring(0, counter + SHORT_URL_CHAR_SIZE);
//        while (counter < numberOfCharsInHash - SHORT_URL_CHAR_SIZE) {
//            if (!DB.exists(hash.substring(counter, counter + SHORT_URL_CHAR_SIZE))) {
//                return hash.substring(counter, counter + SHORT_URL_CHAR_SIZE);
//            }
//            Optional<List<URLModel>> existingHashes =  urlStoreSQLRepository.findByurlHash(urlData.getUrlHash());
//            counter++;
//            return null;
//        }
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
}
