package com.anasajimuhammed.newurl.controllers;

import com.anasajimuhammed.newurl.models.URLModel;
import com.anasajimuhammed.newurl.services.impl.URLOperationsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class URLFindRequestsController {
    private URLOperationsServiceImpl urlOperationsService;

    @GetMapping("{urlHash}")
    public ResponseEntity<Void> getLongURL(@PathVariable String urlHash){
        System.out.println("url hash" + urlHash);
        URLModel originalUrl = urlOperationsService.getURL(urlHash);

        if (originalUrl != null) {
            // Redirect to the original URL
            return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(originalUrl.getOriginalURL())).build();
        } else {
            // Handle the case where the URL does not exist
            return ResponseEntity.notFound().build();
        }
    }
}
