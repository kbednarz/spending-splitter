package com.github.kbednarz.spendingsplitter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpendingSplitterApplication {
    private static final Log log = LogFactory.getLog(SpendingSplitterApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpendingSplitterApplication.class, args);
        log.info("---------- APPLICATION HAS STARTED ----------");
    }
}
