package com.anasajimuhammed.newurl.controllers;
import com.anasajimuhammed.newurl.dto.URLDataResponseModel;
import com.anasajimuhammed.newurl.models.URLModel;
import com.anasajimuhammed.newurl.repository.UrlStoreSQLRepository;
import com.anasajimuhammed.newurl.services.impl.URLOperationsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

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


 }
