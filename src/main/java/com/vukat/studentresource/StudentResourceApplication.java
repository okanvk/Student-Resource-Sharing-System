package com.vukat.studentresource;

import com.vukat.studentresource.domain.University;
import com.vukat.studentresource.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class StudentResourceApplication {


    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


    public static void main(String[] args) {

        SpringApplication.run(StudentResourceApplication.class, args);
    }

}

