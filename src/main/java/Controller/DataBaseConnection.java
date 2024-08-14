package Controller;

import java.sql.*;

public interface DataBaseConnection {
    Connection getConnection() throws SQLException;
}
