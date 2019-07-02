package com.example.issueservice.controller;

import com.example.issueservice.model.Issue;
import com.example.issueservice.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

@RestController
@RequestMapping(path = "/issues")
public class Issues {
    @Autowired
    IssueService issueService;

    @GetMapping
    public Iterable<Issue> index() {
        return issueService.findAll();
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Issue issue) throws URISyntaxException {
        UUID id = issueService.insertOne(issue);
        return ResponseEntity.created(new URI("/issues/" + id)).build();
    }

    @GetMapping(path = "{id}")
    public Issue show(@PathVariable UUID id) {
        return issueService.findOne(id);
    }

    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH}, path = "{id}")
    public ResponseEntity<Void> update(@PathVariable UUID id, @RequestBody Issue issue) {
        issueService.updateOne(id, issue);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> destroy(@PathVariable UUID id) {
        issueService.deleteOne(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
