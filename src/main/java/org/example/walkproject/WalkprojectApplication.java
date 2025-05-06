package org.example.walkproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // JpaAuditing baseEntity 사용시 필요
@SpringBootApplication
public class WalkprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(WalkprojectApplication.class, args);
    }

}
