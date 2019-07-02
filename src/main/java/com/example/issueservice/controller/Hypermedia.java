package com.example.issueservice.controller;

import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/hypermedia")
public class Hypermedia {
    @GetMapping
    public Resource<String> index() {
        return new Resource<>("This is just a test");
    }
}
