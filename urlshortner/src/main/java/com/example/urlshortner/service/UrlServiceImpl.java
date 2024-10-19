package com.example.urlshortner.service;

import com.example.urlshortner.exception.InvalidUrlException;
import com.example.urlshortner.model.Url;
import com.example.urlshortner.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.urlshortner.logic.GenerateShortUrlUtil.getShortUrl;

@Service
public class UrlServiceImpl implements UrlServiceI{
    @Autowired
    private UrlRepository urlRepository;


    public String getOriginalUrl(String shortUrl) {
        Url urlFromDB = urlRepository.findByShortUrl(shortUrl);
        if(urlFromDB == null){
            return null;
        }
        return urlFromDB.getOriginalUrl();
    }

    public Url generateShortUrl(String originalUrl) throws InvalidUrlException {

        // check if original url already exists
        Url urlFromDB = urlRepository.findByOriginalUrl(originalUrl);
        if(urlFromDB!=null){
            return urlFromDB;
        }

        Url urlObject = new Url();
        urlObject.setOriginalUrl(originalUrl);

        String shortUrl = getShortUrl(originalUrl);
        // check for collisions
        while (urlRepository.findByShortUrl(shortUrl) != null){
            String saltedUrl = originalUrl + System.nanoTime();
            shortUrl = getShortUrl(saltedUrl);
        }

        urlObject.setShortUrl(shortUrl);
        return urlRepository.save(urlObject);
    }
}