package task3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
@EntityScan(basePackages = {"Controller"}) //The package in Task2 project that contains all the Entities
public class UdemyClone {
    public static void main(String[] args) {
        SpringApplication.run(UdemyClone.class, args);
    }
}