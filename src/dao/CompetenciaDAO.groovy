package dao
import db.DatabaseConnection
import competencias.Competencia

import java.sql.SQLException

class CompetenciaDAO {
    void inserirCompetencia(Competencia competencia) {
        def conn = DatabaseConnection.getConnection()
        def stmt = null
        try {
            def sql = "INSERT INTO competencias (nome, descricao) VALUES (?, ?)"
            stmt = conn.prepareStatement(sql)

            stmt.setString(1, competencia.nome)
            stmt.setString(2, competencia.descricao)

            int linhasAfetadas = stmt.executeUpdate()
            if (linhasAfetadas > 0) {
                println("Competência inserida com sucesso.")
            }
        } catch (SQLException e) {
            println("Erro ao inserir competência: " + e.message)
        } finally {
            if (stmt != null) stmt.close()
            if (conn != null) conn.close()
        }
    }

    Competencia buscarCompetenciaPorNome(String nome) {
        def conn = DatabaseConnection.getConnection()
        def stmt = null
        Competencia competencia = null
        try {
            def sql = "SELECT * FROM competencias WHERE nome = ?"
            stmt = conn.prepareStatement(sql)
            stmt.setString(1, nome)

            def rs = stmt.executeQuery()
            if (rs.next()) {
                competencia = new Competencia(
                        rs.getString("nome"),
                        rs.getString("descricao")
                )
            }
            rs.close()
        } catch (SQLException e) {
            println("Erro ao buscar competência: " + e.message)
        } finally {
            if (stmt != null) stmt.close()
            if (conn != null) conn.close()
        }
        return competencia
    }


    void atualizarCompetencia(Competencia competencia) {
        def conn = DatabaseConnection.getConnection()
        def stmt = null
        try {
            def sql = "UPDATE competencias SET descricao = ? WHERE nome = ?"
            stmt = conn.prepareStatement(sql)

            stmt.setString(1, competencia.descricao)
            stmt.setString(2, competencia.nome)

            int linhasAfetadas = stmt.executeUpdate()
            if (linhasAfetadas > 0) {
                println("Competência atualizada com sucesso.")
            }
        } catch (SQLException e) {
            println("Erro ao atualizar competência: " + e.message)
        } finally {
            if (stmt != null) stmt.close()
            if (conn != null) conn.close()
        }
    }

    void deletarCompetencia(String nome) {
        def conn = DatabaseConnection.getConnection()
        def stmt = null
        try {
            def sql = "DELETE FROM competencias WHERE nome = ?"
            stmt = conn.prepareStatement(sql)
            stmt.setString(1, nome)

            int linhasAfetadas = stmt.executeUpdate()
            if (linhasAfetadas > 0) {
                println("Competência deletada com sucesso.")
            }
        } catch (SQLException e) {
            println("Erro ao deletar competência: " + e.message)
        } finally {
            if (stmt != null) stmt.close()
            if (conn != null) conn.close()
        }
    }

}


