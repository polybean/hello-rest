package com.example.issueservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@Controller
@RequestMapping
public class Demo {
    @RequestMapping
    public @ResponseBody
    String time() {
        return LocalDateTime.now().toString();
    }
}
