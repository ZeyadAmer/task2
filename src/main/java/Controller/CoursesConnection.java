package Controller;

import org.springframework.stereotype.Component;

import java.sql.*;

@Component("CoursesConnection")
public class CoursesConnection implements DataBaseConnection{
    private static final String URL = "jdbc:mysql://localhost:3306/courses";
    private static final String USER = "root";
    private static final String PASSWORD = "@MindGame@28";
    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
