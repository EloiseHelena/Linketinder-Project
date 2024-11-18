package dao

import db.DatabaseConnection
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

abstract class BaseDAO {
    protected DatabaseConnection databaseConnection

    BaseDAO(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection
    }

    protected void closeResources(Connection conn, PreparedStatement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close()
            if (stmt != null) stmt.close()
            if (conn != null) conn.close()
        } catch (SQLException e) {
            e.printStackTrace()
        }
    }
}
