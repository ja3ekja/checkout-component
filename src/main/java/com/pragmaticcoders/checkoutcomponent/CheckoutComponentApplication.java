package com.pragmaticcoders.checkoutcomponent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class CheckoutComponentApplication {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(CheckoutComponentApplication.class, args);

        String dbURL = "jdbc:sqlserver://localhost:1433;user=sa;password=RybaPila1!PiesJadl";

        Connection conn = DriverManager.getConnection(dbURL);
        if (conn != null) {
            System.out.println("Connected");
        }

    }
}
