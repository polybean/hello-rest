package com.example.issueservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

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
    @ResponseStatus(HttpStatus.CREATED)
    public Params create(@RequestBody Params params) {
        return params;
    }

    @GetMapping(path = "{id}")
    public String show(@PathVariable String id) {
        if (id.equals("13")) {
            throw new NoSuchElementException("id = " + id);
        }

        // Let's try to create a runtime exception
        if (id.equals("0")) {
            int i = 2 / 0;
            System.out.println("I am doing fantastic math, 2 / 0 = " + i);
        }


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
