package com.example.urlshortner.controller;

import com.example.urlshortner.dto.UserRequestDTO;
import com.example.urlshortner.exception.UserAlreadyRegisteredException;
import com.example.urlshortner.model.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.urlshortner.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addOrUpdate")
    public ResponseEntity<User> addOrUpdateUser(@RequestBody @Valid UserRequestDTO userRequestDTO) throws UserAlreadyRegisteredException {
        User user = userService.addOrUpdateUser(userRequestDTO);

        if(user != null){
            return ResponseEntity.ok(user);
        }
        else{
            return ResponseEntity.badRequest().build();
        }
    }
}
