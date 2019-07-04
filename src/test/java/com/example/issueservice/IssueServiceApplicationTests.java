package com.example.issueservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IssueServiceApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    DatePrinter datePrinter;

    // hijack timeFactory Bean already in the context
    @MockBean
    TimeFactory timeFactory;

    @Test
    public void datePrinterWorks() {
        given(timeFactory.now()).willReturn(LocalDateTime.of(2019, 7, 15, 9, 41, 30));
        assertEquals("Now it is 2019-07-15T09:41:30", datePrinter.getDate());
    }
}
