package dao

import db.DatabaseConnection
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import java.util.logging.Level
import java.util.logging.Logger

abstract class BaseDAO<T> {

    protected DatabaseConnection databaseConnection
    private static final Logger LOGGER = Logger.getLogger(BaseDAO.class.name)

    BaseDAO(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection
    }

    protected T executeQuery(String query, Object... params) {
        Connection conn = null
        PreparedStatement stmt = null
        ResultSet rs = null
        T result = null

        try {
            conn = databaseConnection.getConnection()
            stmt = conn.prepareStatement(query)

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i])
            }

            rs = stmt.executeQuery()

            if (rs.next()) {
                result = mapResultSet(rs)
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao executar consulta: " + query, e)
        } finally {
            closeResources(conn, stmt, rs)
        }
        return result
    }

    protected int executeUpdate(String query, Object... params) {
        Connection conn = null
        PreparedStatement stmt = null
        int rowsAffected = 0

        try {
            conn = databaseConnection.getConnection()
            stmt = conn.prepareStatement(query)

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i])
            }

            rowsAffected = stmt.executeUpdate()
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao executar update: " + query, e)
        } finally {
            closeResources(conn, stmt, null)
        }
        return rowsAffected
    }


    protected boolean executeBatchUpdate(List<String> queries, List<List<Object>> paramsList) {
        Connection conn = null
        PreparedStatement stmt = null
        boolean success = false

        try {
            conn = databaseConnection.getConnection()
            conn.setAutoCommit(false)

            for (int i = 0; i < queries.size(); i++) {
                stmt = conn.prepareStatement(queries.get(i))
                List<Object> params = paramsList.get(i)
                for (int j = 0; j < params.size(); j++) {
                    stmt.setObject(j + 1, params.get(j))
                }
                stmt.addBatch()
            }

            int[] results = stmt.executeBatch()
            conn.commit()  // Confirma a transação

            success = results.every { it >= 0 }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao executar batch update", e)
            if (conn != null) {
                try {
                    conn.rollback()  // Reverte a transação em caso de erro
                } catch (SQLException rollbackEx) {
                    LOGGER.log(Level.SEVERE, "Erro ao fazer rollback", rollbackEx)
                }
            }
        } finally {
            closeResources(conn, stmt, null)
        }
        return success
    }


    protected void closeResources(Connection conn, PreparedStatement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close()
            if (stmt != null) stmt.close()
            if (conn != null) conn.close()
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Erro ao fechar recursos", e)
        }
    }

    abstract T mapResultSet(ResultSet rs)
}


