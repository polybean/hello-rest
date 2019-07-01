package com.example.issueservice.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/issues")
public class Issues {
    static class Params {
        String author;
        int type;

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getAuthor() {
            return author;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }
    }

    @GetMapping
    public Map<String, String> index(@RequestParam String author, @RequestParam int type) {
        Map<String, String> body = new HashMap<>();
        body.put("author", author);
        body.put("type", String.valueOf(type));

        return body;
    }

    @GetMapping(path = "params")
    public Map<String, String> indexUsingParamStructure(Params params) {
        Map<String, String> body = new HashMap<>();
        body.put("author", params.author);
        body.put("type", String.valueOf(params.type));

        return body;
    }

    @PostMapping
    public String create() {
        return "create";
    }

    @GetMapping(path = "{id}")
    public String show(@PathVariable String id) {
        return "show" + id;
    }

    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH}, path = "{id}")
    public String update(@PathVariable String id) {
        return "update" + id;
    }

    @DeleteMapping(path = "{id}")
    public String destroy(@PathVariable String id) {
        return "destroy" + id;
    }
}
