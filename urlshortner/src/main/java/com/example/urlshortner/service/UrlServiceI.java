package com.example.urlshortner.service;

import com.example.urlshortner.model.Url;

public interface UrlServiceI {
    String getOriginalUrl(String id);
    Url generateShortUrl(String url);
}