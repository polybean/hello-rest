package com.example.issueservice.service;

import com.example.issueservice.model.Issue;
import com.example.issueservice.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class IssueService {
    @Autowired
    IssueRepository issueRepository;

    public UUID insertOne(Issue issue) {
        return issueRepository.save(issue).getId();
    }

    public Issue findOne(UUID id) {
        return issueRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException(id.toString()));
    }

    public Iterable<Issue> findAll() {
        return issueRepository.findAll();
    }

    public Issue updateOne(UUID id, Issue issue) {
        // Verify the existence of the record
        // If the id is not valid, NoSuchElementException will be thrown, leading to 404 response
        findOne(id);
        issue.setId(id);
        // Do the update
        return issueRepository.save(issue);
    }

    public void deleteOne(UUID id) {
        // Verify the existence of the record
        // If the id is not valid, NoSuchElementException will be thrown, leading to 404 response
        findOne(id);
        issueRepository.deleteById(id);
    }
}
