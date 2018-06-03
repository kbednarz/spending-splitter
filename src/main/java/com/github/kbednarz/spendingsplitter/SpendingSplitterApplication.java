package com.github.kbednarz.spendingsplitter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpendingSplitterApplication {
    private static final Logger log = LoggerFactory.getLogger(SpendingSplitterApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpendingSplitterApplication.class, args);
        log.info("---------- APPLICATION HAS STARTED ----------");
    }
}
