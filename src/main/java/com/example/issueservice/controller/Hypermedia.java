package com.example.issueservice.controller;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.BasicLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/hypermedia")
public class Hypermedia {
    @GetMapping
    public Resource<String> index() {
        return new Resource<>("This is just a test",
                BasicLinkBuilder.linkToCurrentMapping().withSelfRel(),
                BasicLinkBuilder.linkToCurrentMapping().slash("issues").withRel("issues"),
                linkTo(methodOn(Issues.class).index()).withRel("issues2"));
    }
}
