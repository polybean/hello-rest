package com.example.issueservice.controller;

import com.example.issueservice.model.Issue;
import com.example.issueservice.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.BasicLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/issues")
public class Issues {
    @Autowired
    IssueService issueService;

    @Autowired
    IssueResourceAssembler issueAssembler;

    @GetMapping
    public Resources<Issue> index() {
        return new Resources<>(issueService.findAll());
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Issue issue) {
        UUID id = issueService.insertOne(issue);
        return ResponseEntity.created(
                BasicLinkBuilder
                        .linkToCurrentMapping()
                        .slash("issues")
                        .slash(id).toUri()
        ).build();
    }

    @GetMapping(path = "{id}")
    public Resource<Issue> show(@PathVariable UUID id) {
        return issueAssembler.toResource(issueService.findOne(id));
    }

    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH}, path = "{id}")
    public Resource<Issue> update(@PathVariable UUID id, @RequestBody Issue issue) {
        return issueAssembler.toResource(issueService.updateOne(id, issue));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> destroy(@PathVariable UUID id) {
        issueService.deleteOne(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Component
    public static class IssueResourceAssembler implements ResourceAssembler<Issue, Resource<Issue>> {

        @Override
        public Resource<Issue> toResource(Issue issue) {
            UUID id = issue.getId();
            return new Resource<>(issue,
                    linkTo(methodOn(Issues.class).show(id)).withSelfRel(),
                    linkTo(methodOn(Subscriptions.class).index(id)).withRel("subscription"),
                    linkTo(methodOn(Progresses.class).index(id)).withRel("progress")
            );
        }
    }
}
