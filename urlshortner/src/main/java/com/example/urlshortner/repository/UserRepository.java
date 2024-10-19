package com.example.urlshortner.repository;
import com.example.urlshortner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

}
