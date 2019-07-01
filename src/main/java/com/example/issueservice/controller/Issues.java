package com.example.issueservice.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/issues")
public class Issues {
    @GetMapping
    public Map<String, String> index(@RequestParam String author, @RequestParam int type) {
        Map<String, String> body = new HashMap<>();
        body.put("author", author);
        body.put("type", String.valueOf(type));

        return body;
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
