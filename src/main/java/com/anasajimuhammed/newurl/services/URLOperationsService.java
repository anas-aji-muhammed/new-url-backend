package com.anasajimuhammed.newurl.services;

import com.anasajimuhammed.newurl.models.ClickEvents;
import com.anasajimuhammed.newurl.models.URLModel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface URLOperationsService {
    public String shortenURL(String url);
    public URLModel addNewURL(URLModel urlData);

    URLModel updateURL(URLModel urlData);

    public void deleteURL(URLModel urlData);
    public URLModel getURL(String url);
    public List<URLModel> getURLs();



    void urlClickEventAdd(ClickEvents clickEvent);
    List<ClickEvents> getUrlAnalytics(Long id, LocalDateTime start, LocalDateTime end);
}
