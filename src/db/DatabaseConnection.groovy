package db

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class DatabaseConnection {
    static Connection getConnection() {
        def url = "jdbc:postgresql://localhost:5432/linketinder_db"
        def user = "postgres"
        def password = "acelera"

        try {
            return DriverManager.getConnection(url, user, password)
        } catch (SQLException e) {
            e.printStackTrace()
            throw new RuntimeException("Erro ao conectar ao banco de dados")
        }
    }
}
