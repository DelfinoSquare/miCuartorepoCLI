/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joel Grullon
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQL {
    private static Properties props;

    private static void loadProperties() {
        if (props == null) {
            props = new Properties();
            try (FileInputStream fis = new FileInputStream("config.properties")) {
                props.load(fis);
            } catch (IOException e) {
                System.err.println("Error cargando config.properties: " + e.getMessage());
            }
        }
    }

    public static Connection getConnection() throws SQLException {
        loadProperties();
        String dbUrl = props.getProperty("DB_URL");
        String dbUser = props.getProperty("DB_USER");
        String dbPass = props.getProperty("DB_PASSWORD");

        return DriverManager.getConnection(dbUrl, dbUser, dbPass);
    }
}