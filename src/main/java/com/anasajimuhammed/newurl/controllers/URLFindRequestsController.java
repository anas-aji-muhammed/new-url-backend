package com.anasajimuhammed.newurl.controllers;

import com.anasajimuhammed.newurl.models.ClickEvents;
import com.anasajimuhammed.newurl.models.URLModel;
import com.anasajimuhammed.newurl.services.impl.URLOperationsServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class URLFindRequestsController {
    private URLOperationsServiceImpl urlOperationsService;

    @GetMapping("{urlHash}")
    public ResponseEntity<Void> getLongURL(
            @PathVariable String urlHash, @RequestParam("type") Optional<String> isQr,
            HttpServletRequest httpServletRequest
    ){
        System.out.println("url hash" + urlHash);
        System.out.println("user agent" + httpServletRequest.getHeader("User-Agent"));
        URLModel originalUrl = urlOperationsService.getURL(urlHash);

        if (originalUrl != null) {
            ClickEvents clickEvent = new ClickEvents();
            clickEvent.setUrlId(originalUrl.getId());
            clickEvent.setUserAgent(httpServletRequest.getHeader("User-Agent"));
            if(isQr.isPresent()){
                clickEvent.setLinkSource("qr");
            }
            else{
                clickEvent.setLinkSource("link");
            }

            urlOperationsService.urlClickEventAdd(clickEvent);
            originalUrl.setCount(originalUrl.getCount()+1);
            urlOperationsService.updateURL(originalUrl);
            // Redirect to the original URL

            return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(originalUrl.getOriginalURL())).build();
        } else {
            // Handle the case where the URL does not exist
            return ResponseEntity.notFound().build();
        }
    }
}
