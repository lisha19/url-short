package com.example.urlshortner.controller;


import com.example.urlshortner.model.Url;
import com.example.urlshortner.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("url/shortner")
@CrossOrigin(origins = "http://localhost:3000")
public class UrlController {

    @Autowired
    private UrlService urlService;


@GetMapping("/{id}")
public ResponseEntity<Void> redirect(@PathVariable String id) {
    String originalUrl = urlService.getOriginlUrl(id); // Get the original URL from the service
    if (originalUrl == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Not found
    }
    return ResponseEntity.status(HttpStatus.FOUND) // Use 302 for redirect
            .location(URI.create(originalUrl)) // Set the location header
            .build();
}



    @PostMapping
    public ResponseEntity<Url> generateShortUrl(@RequestBody Map<String, String> body) {
        String originalUrl = body.get("url");
        Url generatedUrl = urlService.generateShortUrl(originalUrl);
        if (generatedUrl == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(generatedUrl);
    }


}
