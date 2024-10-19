package com.example.urlshortner.service;

import com.example.urlshortner.exception.InvalidUrlException;
import com.example.urlshortner.model.Url;

public interface UrlServiceI {
    String getOriginalUrl(String id);
    Url generateShortUrl(String url) throws InvalidUrlException;
}