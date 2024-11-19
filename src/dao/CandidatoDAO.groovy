package dao
import db.DatabaseConnection
import models.Candidato

import java.sql.ResultSet
import java.sql.SQLException

class CandidatoDAO extends BaseDAO {

    CandidatoDAO(DatabaseConnection databaseConnection) {
        super(databaseConnection)
    }

    @Override
    def mapResultSet(ResultSet rs) {
        return null
    }

    void cadastrarCandidato(Candidato candidato) {
        def conn = null
        def stmt = null
        try {
            conn = databaseConnection.getConnection()
            def sql = "INSERT INTO candidatos (nome, sobrenome, email, cpf, data_nascimento, pais, cep, descricao) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
            stmt = conn.prepareStatement(sql)

            stmt.setString(1, candidato.nome)
            stmt.setString(2, candidato.sobrenome)
            stmt.setString(3, candidato.email)
            stmt.setString(4, candidato.cpf)
            stmt.setDate(5, java.sql.Date.valueOf(candidato.dataNascimento))
            stmt.setString(6, candidato.pais)
            stmt.setString(7, candidato.cep)
            stmt.setString(8, candidato.descricao)

            int linhasAfetadas = stmt.executeUpdate()
            if (linhasAfetadas > 0) {
                println("Candidato inserido com sucesso.")
            }
        } catch (SQLException e) {
            println("Erro ao inserir candidato: " + e.message)
        } finally {
            closeResources(conn, stmt, null)
        }
    }

    List<Candidato> listarTodos() {
        List<Candidato> candidatos = []
        def conn = null
        def stmt = null
        def rs = null
        try {
            conn = databaseConnection.getConnection()
            String sql = "SELECT id, nome, sobrenome, data_nascimento, email, cpf, pais, cep, descricao, senha FROM public.candidatos;"
            stmt = conn.prepareStatement(sql)
            rs = stmt.executeQuery()

            while (rs.next()) {
                Candidato candidato = new Candidato(
                        rs.getString("nome"),
                        rs.getString("sobrenome"),
                        rs.getString("data_nascimento"),
                        rs.getString("email"),
                        rs.getString("cpf"),
                        rs.getString("pais"),
                        rs.getString("cep"),
                        rs.getString("descricao")
                )
                candidatos.add(candidato)
            }
        } catch (SQLException e) {
            println("Erro ao listar candidatos: " + e.message)
        } finally {
            closeResources(conn, stmt, rs)
        }

        return candidatos
    }

    Candidato buscarCandidatoPorCpf(String cpf) {
        def conn = null
        def stmt = null
        def rs = null
        Candidato candidato = null
        try {
            conn = databaseConnection.getConnection()
            def sql = "SELECT * FROM candidatos WHERE cpf = ?"
            stmt = conn.prepareStatement(sql)
            stmt.setString(1, cpf)
            rs = stmt.executeQuery()

            if (rs.next()) {
                candidato = new Candidato(
                        rs.getString("nome"),
                        rs.getString("sobrenome"),
                        rs.getString("email"),
                        rs.getString("cpf"),
                        rs.getDate("data_nascimento").toLocalDate(),
                        rs.getString("pais"),
                        rs.getString("cep"),
                        rs.getString("descricao")
                )
            }
        } catch (SQLException e) {
            println("Erro ao buscar candidato: " + e.message)
        } finally {
            closeResources(conn, stmt, rs)
        }
        return candidato
    }

    void atualizarCandidato(Candidato candidato) {
        def conn = null
        def stmt = null
        try {
            conn = databaseConnection.getConnection()
            def sql = "UPDATE candidatos SET nome = ?, sobrenome = ?, email = ?, data_nascimento = ?, pais = ?, cep = ?, descricao = ? WHERE cpf = ?"
            stmt = conn.prepareStatement(sql)

            stmt.setString(1, candidato.nome)
            stmt.setString(2, candidato.sobrenome)
            stmt.setString(3, candidato.email)
            stmt.setDate(4, java.sql.Date.valueOf(candidato.dataNascimento))
            stmt.setString(5, candidato.pais)
            stmt.setString(6, candidato.cep)
            stmt.setString(7, candidato.descricao)
            stmt.setString(8, candidato.cpf)

            stmt.executeUpdate()
            println("Candidato atualizado com sucesso.")
        } catch (SQLException e) {
            println("Erro ao atualizar candidato: " + e.message)
        } finally {
            closeResources(conn, stmt, null)
        }
    }

    void deletarCandidato(String cpf) {
        def conn = null
        def stmt = null
        try {
            conn = databaseConnection.getConnection()
            def sql = "DELETE FROM candidatos WHERE cpf = ?"
            stmt = conn.prepareStatement(sql)
            stmt.setString(1, cpf)

            stmt.executeUpdate()
            println("Candidato deletado com sucesso.")
        } catch (SQLException e) {
            println("Erro ao deletar candidato: " + e.message)
        } finally {
            closeResources(conn, stmt, null)
        }
    }
}

