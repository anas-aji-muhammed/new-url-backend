package com.anasajimuhammed.newurl.services;

import com.anasajimuhammed.newurl.models.URLModel;

import java.util.List;
import java.util.Optional;

public interface URLOperationsService {
    public String shortenURL(String url);
    public URLModel addNewURL(URLModel urlData);

    URLModel updateURL(URLModel urlData);

    public void deleteURL(URLModel urlData);
    public URLModel getURL(String url);
    public List<URLModel> getURLs();



}
