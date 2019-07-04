package com.example.issueservice;


import com.example.issueservice.util.Fibonacci;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
@SpringBootTest
public class ParameterizedTests {
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0, 0}, {1, 1}, {2, 1}, {3, 2}, {4, 3}, {5, 5}, {6, 8}
        });
    }

    private int fInput;
    private int fExpected;

    public ParameterizedTests(int input, int expected) {
        this.fInput = input;
        this.fExpected = expected;
    }

    @Test
    public void parameterized() {
        assertEquals(fExpected, Fibonacci.compute(fInput));
    }
}


