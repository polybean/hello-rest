package com.example.issueservice;

import com.example.issueservice.model.Issue;
import com.example.issueservice.service.IssueService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.hateoas.mvc.BasicLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.PostConstruct;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IssuesControllerTests {
    @Autowired
    MockMvc mvc;

    @SpyBean
    IssueService issueService;

    @Autowired
    ObjectMapper mapper;

    @PostConstruct
    public void postConstruct() {
        mapper.registerModule(new Jackson2HalModule());
    }

    @Test
    public void VerifyMethodInvocation_GetAllIssues_IssueServiceFindAllIsCalled() throws Exception {
        mvc.perform(get("/issues"));
        Mockito.verify(issueService).findAll();
    }


    @Test
    public void CreateOneIssue_UsingPostCall_ReturnCreatedResponseWithLocationHeader() throws Exception {
        Issue issue = new Issue("Make it shinny!", "John", 1);

        String location = mvc
                .perform(
                        post("/issues")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(mapper.writeValueAsString(issue))
                )
                .andExpect(
                        status()
                                .isCreated()

                )
                .andReturn()
                .getResponse()
                .getHeader(HttpHeaders.LOCATION);

        assertNotNull(location);
    }


    @Test
    public void RetrieveOneIssue_OneIssueRecordPopulatedInDB_ReturnTheHALIssueResource() throws Exception {
        Issue issue = new Issue("Make it shinny!", "John", 1);
        UUID id = issueService.insertOne(issue);
        String location = BasicLinkBuilder
                .linkToCurrentMapping()
                .slash("issues")
                .slash(id)
                .toUri()
                .toString();

        String result = mvc.
                perform(
                        get(location)
                )
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Resource<Issue> output = mapper.readValue(result,
                new TypeReference<Resource<Issue>>() {
                });

        assertNotNull(output.getContent());
        assertResourceEquals(issue, output);
    }

    @Test
    public void RetrieveAllIssues_PopulatedWithIssueRecords_ReturnAllIssues() throws Exception {
        Issue issue = new Issue("Make it shinny!", "John", 1);
        issueService.insertOne(issue);

        issue = new Issue("Fix the bug!", "Sean", 2);
        issueService.insertOne(issue);

        String result = mvc.perform(
                get("/issues")
        ).andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Resources<Issue> output = mapper.readValue(result, new TypeReference<Resources<Issue>>() {
        });

        assertTrue(output.getContent().size() >= 2);
        assertTrue(output.getContent().stream().anyMatch(i ->
                i.getDescription().equals("Make it shinny!")));
        assertTrue(output.getContent().stream().anyMatch(i ->
                i.getDescription().equals("Fix the bug!")));
    }

    private void assertResourceEquals(Issue input, Resource<Issue> result) {
        assertEquals(input.getDescription(), result.getContent().getDescription());
        assertEquals(input.getDescription(), result.getContent().getDescription());
        assertEquals(input.getDescription(), result.getContent().getDescription());
    }
}
