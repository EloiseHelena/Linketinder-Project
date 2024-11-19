package dao

import db.DatabaseConnection
import models.Vaga
import java.sql.ResultSet
import java.sql.SQLException

class VagaDAO extends BaseDAO<Vaga> {

    VagaDAO(DatabaseConnection databaseConnection) {
        super(databaseConnection)
    }

    void inserirVaga(Vaga vaga) {
        def sql = "INSERT INTO vagas (titulo, descricao, salario, requisitos, empresa_cnpj) VALUES (?, ?, ?, ?, ?)"
        def linhasAfetadas = executeUpdate(sql, vaga.titulo, vaga.descricao, vaga.salario, vaga.requisitos, vaga.empresaCnpj)

        if (linhasAfetadas > 0) {
            println("Vaga inserida com sucesso.")
        }
    }

    Vaga buscarVagaPorTitulo(String titulo) {
        def sql = "SELECT * FROM vagas WHERE titulo = ?"
        return executeQuery(sql, titulo)
    }

    void atualizarVaga(Vaga vaga) {
        def sql = "UPDATE vagas SET descricao = ?, salario = ?, requisitos = ?, empresa_cnpj = ? WHERE titulo = ?"
        def linhasAfetadas = executeUpdate(sql, vaga.descricao, vaga.salario, vaga.requisitos, vaga.empresaCnpj, vaga.titulo)

        if (linhasAfetadas > 0) {
            println("Vaga atualizada com sucesso.")
        }
    }

    void deletarVaga(String titulo) {
        def sql = "DELETE FROM vagas WHERE titulo = ?"
        def linhasAfetadas = executeUpdate(sql, titulo)

        if (linhasAfetadas > 0) {
            println("Vaga deletada com sucesso.")
        }
    }

    @Override
    Vaga mapResultSet(ResultSet rs) {
        return new Vaga(
                rs.getString("titulo"),
                rs.getString("descricao"),
                rs.getBigDecimal("salario"),
                rs.getString("requisitos"),
                rs.getString("empresa_cnpj")
        )
    }
}

