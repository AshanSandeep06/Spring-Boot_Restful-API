package lk.epic.restfulAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.annotation.Validated;

@SpringBootApplication
@Validated
public class WebAppInitializer {
    public static void main(String[] args) {
        SpringApplication.run(WebAppInitializer.class);
    }
}
