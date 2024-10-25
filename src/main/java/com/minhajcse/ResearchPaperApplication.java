package com.minhajcse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

import javax.sql.DataSource;
import java.sql.*;

@SpringBootApplication
@EnableJdbcRepositories(basePackages = "com.minhajcse.repository")
public class ResearchPaperApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResearchPaperApplication.class, args);

    }

}
