package eu.iba.auto_test.converterbnb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class ConverterBnbApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConverterBnbApplication.class, args);
    }
}
