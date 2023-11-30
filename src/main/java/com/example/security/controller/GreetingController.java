package com.example.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class GreetingController {

    @GetMapping("/hello")
    public ResponseEntity<String > sayHello(){
        return ResponseEntity.ok("Hellow ");
    }
    @GetMapping("/goodby")
    public ResponseEntity<String > sayGoodBy(){
        return ResponseEntity.ok("say Good By ");
    }
}
