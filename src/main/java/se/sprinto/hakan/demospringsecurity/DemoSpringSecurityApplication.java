package se.sprinto.hakan.demospringsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "se.sprinto.hakan")
public class DemoSpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoSpringSecurityApplication.class, args);
    }

}
