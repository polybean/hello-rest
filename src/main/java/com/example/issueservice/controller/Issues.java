package com.example.issueservice.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/issues")
public class Issues {
    @GetMapping
    public String index() {
        return "index";
    }

    @PostMapping
    public String create() {
        return "create";
    }

    @GetMapping(path = "1")
    public String show() {
        return "show";
    }

    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH}, path = "1")
    public String update() {
        return "update";
    }

    @DeleteMapping(path = "1")
    public String destroy() {
        return "destroy";
    }
}
