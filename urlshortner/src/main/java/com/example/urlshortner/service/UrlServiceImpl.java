package com.example.urlshortner.service;

import com.example.urlshortner.model.Url;

public interface UrlServiceImpl {
    String getOriginalUrl(String id);
    Url generateShortUrl(String url);
}