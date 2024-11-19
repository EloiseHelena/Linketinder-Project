package dao

import db.DatabaseConnection
import models.Competencia
import java.sql.ResultSet
import java.sql.SQLException

class CompetenciaDAO extends BaseDAO<Competencia> {

    CompetenciaDAO(DatabaseConnection databaseConnection) {
        super(databaseConnection)
    }

    void inserirCompetencia(Competencia competencia) {
        def sql = "INSERT INTO competencias (nome, descricao) VALUES (?, ?)"
        def linhasAfetadas = executeUpdate(sql, competencia.nome, competencia.descricao)

        if (linhasAfetadas > 0) {
            println("Competência inserida com sucesso.")
        }
    }

    Competencia buscarCompetenciaPorNome(String nome) {
        def sql = "SELECT * FROM competencias WHERE nome = ?"
        return executeQuery(sql, nome)
    }

    void atualizarCompetencia(Competencia competencia) {
        def sql = "UPDATE competencias SET descricao = ? WHERE nome = ?"
        def linhasAfetadas = executeUpdate(sql, competencia.descricao, competencia.nome)

        if (linhasAfetadas > 0) {
            println("Competência atualizada com sucesso.")
        }
    }

    void deletarCompetencia(String nome) {
        def sql = "DELETE FROM competencias WHERE nome = ?"
        def linhasAfetadas = executeUpdate(sql, nome)

        if (linhasAfetadas > 0) {
            println("Competência deletada com sucesso.")
        }
    }

    @Override
    Competencia mapResultSet(ResultSet rs) {
        // Mapeando os resultados do ResultSet para o modelo Competencia
        return new Competencia(
                rs.getString("nome"),
                rs.getString("descricao")
        )
    }
}



