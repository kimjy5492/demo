package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("스프링부트 관리 Bean");

        Connection conn = dataSource.getConnection();

        PreparedStatement ps = conn.prepareStatement("SELECT code, name FROM world.country");
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            String code = rs.getString("code");
            String name = rs.getString("name");

            System.out.println(code + " " + name);
        }

        rs.close();
        ps.close();
        conn.close();
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
