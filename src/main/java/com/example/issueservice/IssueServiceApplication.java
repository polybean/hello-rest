package com.example.issueservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

@SpringBootApplication
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
@RestController
public class IssueServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(IssueServiceApplication.class, args);
    }

    @GetMapping
    public ResponseEntity<String> index() throws UnknownHostException {
        String hostname = InetAddress.getLocalHost().getHostName();

        return ResponseEntity.ok("Goodbye, world! @" + hostname);
    }

}

@Component
class DatePrinter {
    @Autowired
    private TimeFactory timeFactory;

    public String getDate() {
        return "Now it is " + timeFactory.now();
    }
}

class TimeFactory {
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}

@Configuration
class MyConfiguration {
    @Bean
    public TimeFactory timeFactory() {
        return new TimeFactory();
    }
}
