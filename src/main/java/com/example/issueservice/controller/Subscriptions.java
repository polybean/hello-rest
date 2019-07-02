package com.example.issueservice.controller;

import com.example.issueservice.model.Subscription;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "/issues/{id}/subscriptions")
public class Subscriptions {
    @GetMapping
    public Resources<Subscription> index(@PathVariable UUID id) {
        return null;
    }
}
