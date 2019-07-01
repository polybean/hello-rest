package com.example.issueservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping
public class Demo {
    @RequestMapping
    public LocalDateTime time() {
        return LocalDateTime.now();
    }
}
