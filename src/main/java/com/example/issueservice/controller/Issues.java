package com.example.issueservice.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class Issues {
    @GetMapping(path = "/issues")
    public String index() {
        return "index";
    }

    @PostMapping(path = "/issues")
    public String create() {
        return "create";
    }

    @GetMapping(path = "issues/1")
    public String show() {
        return "show";
    }

    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH}, path = "/issues/1")
    public String update() {
        return "update";
    }

    @DeleteMapping(path = "/issues/1")
    public String destroy() {
        return "destroy";
    }
}
