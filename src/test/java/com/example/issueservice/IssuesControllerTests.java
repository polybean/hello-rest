package com.example.issueservice;

import com.example.issueservice.service.IssueService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IssuesControllerTests {
    @Autowired
    MockMvc mvc;

    @SpyBean
    IssueService issueService;

    @Test
    public void VerifyMethodInvocation_GetAllIssues_IssueServiceFindAllIsCalled() throws Exception {
        mvc.perform(get("/issues"));
        Mockito.verify(issueService).findAll();
    }
}
