package com.example.urlshortner.repository;

//import com.codelogic.URL_shortner.modal.Url;
import com.example.urlshortner.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UrlRepository extends JpaRepository<Url,Integer> {

//    @Query(value = "select originalurl from Url where shorturl = ?1", nativeQuery = true)
    Url findByShortUrl(String shortUrl);
}