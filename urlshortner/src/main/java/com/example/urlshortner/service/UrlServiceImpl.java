package com.example.urlshortner.service;

import com.example.urlshortner.model.Url;
import com.example.urlshortner.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.urlshortner.logic.GenerateShortUrl.getShortUrl;
import static com.example.urlshortner.logic.GenerateShortUrl.isUrlValid;

@Service
public class UrlServiceImpl implements UrlServiceI{
    @Autowired
    private UrlRepository urlRepository;


    public String getOriginalUrl(String shortUrl) {
        return urlRepository.findByShortUrl(shortUrl).getOriginalUrl();
    }


    public Url generateShortUrl(String url) {
        if(! isUrlValid(url)) {
            System.out.println("URL is not valid");
            return null;
        }

        Url urlObject = new Url();
        urlObject.setOriginalUrl(url);
        urlObject.setShortUrl(getShortUrl(url));

        return urlRepository.save(urlObject);
    }
}