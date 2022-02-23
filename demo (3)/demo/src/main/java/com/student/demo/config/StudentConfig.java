package com.student.demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(

            com.student.demo.repository.StudentRepository repository
    ){
        return args -> {
            new com.student.demo.entity.Student(
                    "maria",
                    "maria@gmail.com",
                    LocalDate.of(2003, Month.JANUARY,26));
            new com.student.demo.entity.Student(
                    "alex",
                    "alex@gmail.com",
                    LocalDate.of(2000, Month.JANUARY,26)
            );
//            repository.saveAll(
//                    List.of(maria,alex)
//            );
        };

    }
}

