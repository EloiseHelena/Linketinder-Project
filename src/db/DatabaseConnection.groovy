package db

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

interface DatabaseConnection {
    Connection getConnection()
}

class PostgresConnection implements DatabaseConnection {
    private String url = "jdbc:postgresql://localhost:5432/linketinder_db"
    private String user = "postgres"
    private String password = "acelera"

    @Override
    Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password)
        } catch (SQLException e) {
            e.printStackTrace()
            throw new RuntimeException("Erro ao conectar ao banco de dados: " + e.message)
        }
    }
}
