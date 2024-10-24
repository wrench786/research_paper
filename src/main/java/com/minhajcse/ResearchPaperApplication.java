package com.minhajcse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.sql.*;

@SpringBootApplication
public class ResearchPaperApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResearchPaperApplication.class, args);


        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/research_paper", "postgres", "postgres")){

            PreparedStatement ps =  connection.prepareStatement("select * from \"User\"");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(1023);
            }

            PreparedStatement insertPs = connection.prepareStatement("insert into \"User\" (userName, full_name, age) values(?,?,?)");
            insertPs.setString(1, "wrench787");
            insertPs.setString(2, "Md. Minhajul Islam");
            insertPs.setInt(3,20);

            int insertCount = insertPs.executeUpdate();
            System.out.println(insertCount);

            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("age"));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

}
