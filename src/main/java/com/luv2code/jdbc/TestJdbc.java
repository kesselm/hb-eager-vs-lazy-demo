package com.luv2code.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class TestJdbc {

    public static void main(String[] args){
        String url = "jdbc:postgresql://localhost:5432/hb_student_tracker";
        Properties props = new Properties();
        props.setProperty("user","postgres");
        props.setProperty("password","DPU3Dfig");
        //props.setProperty("ssl","true");
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Connecting to database: " + url);
            Connection conn = DriverManager.getConnection(url, props);
            System.out.println("Connection successful!!!");
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
