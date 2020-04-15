package com.mumacj.personnelstatisticsbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mumacj.personnelstatisticsbackend.dao")
public class PersonnelStatisticsBackEndApplication {
    public static void main(String[] args) {
        SpringApplication.run(PersonnelStatisticsBackEndApplication.class, args);
    }

}