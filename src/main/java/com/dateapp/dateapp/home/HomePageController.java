package com.dateapp.dateapp.home;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomePageController {
    @GetMapping("/")
    ResponseEntity<String> test(){
       return ResponseEntity.ok().body("test");
    }
}
