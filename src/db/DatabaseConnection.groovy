package db

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException


interface DatabaseConnection {
    Connection getConnection()
}

class PostgresConnection implements DatabaseConnection {
    private static final String URL = System.getenv('DB_URL') ?: "jdbc:postgresql://localhost:5432/linketinder_db"
    private static final String USER = System.getenv('DB_USER') ?: "postgres"
    private static final String PASSWORD = System.getenv('DB_PASSWORD') ?: "acelera"

    @Override
    Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD)
        } catch (SQLException e) {

            logError("Erro ao conectar ao banco de dados", e)
            throw new RuntimeException("Erro ao conectar ao banco de dados: " + e.message)
        }
    }

    private void logError(String message, Throwable e) {

        // Implementações futuras

        println("$message: ${e.message}")
        e.printStackTrace()
    }
}
