package dao
import db.DatabaseConnection
import candidatos.Candidato
import empresa.Empresa

import java.sql.SQLException

class CandidatoDAO {
    void cadastrarCandidato(Candidato candidato) {
        def conn = DatabaseConnection.getConnection()
        def stmt = null
        try {
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
            // Com Id do canditato criado, criar um registro de candidatos_competencias relacionando o candidato com suas competencias
            if (linhasAfetadas > 0) {
                println("Candidato inserido com sucesso.")
            }
        } catch (SQLException e) {
            println("Erro ao inserir candidato: " + e.message)
        } finally {
            if (stmt != null) stmt.close()
            if (conn != null) conn.close()
        }
    }

    public List<Candidato> listarTodos() {
        List<Candidato> candidatos = new ArrayList<>();
        def conn = DatabaseConnection.getConnection();
        String sql = "SELECT id, nome, sobrenome, data_nascimento, email, cpf, pais, cep, descricao, senha FROM public.candidatos;"
        def stmt = conn.prepareStatement(sql);

        def rs = stmt.executeQuery();
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
            );
            candidatos.add(candidato);
        }
        rs.close();
        stmt.close();
        conn.close();

        return candidatos;
    }

    Candidato buscarCandidatoPorCpf(String cpf) {
        def conn = DatabaseConnection.getConnection()
        def sql = "SELECT * FROM candidatos WHERE cpf = ?"
        def stmt = conn.prepareStatement(sql)
        stmt.setString(1, cpf)

        def rs = stmt.executeQuery()
        Candidato candidato = null
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
        rs.close()
        stmt.close()
        conn.close()
        return candidato
    }

    void atualizarCandidato(Candidato candidato) {
        def conn = DatabaseConnection.getConnection()
        def sql = "UPDATE candidatos SET nome = ?, sobrenome = ?, email = ?, data_nascimento = ?, pais = ?, cep = ?, descricao = ? WHERE cpf = ?"
        def stmt = conn.prepareStatement(sql)

        stmt.setString(1, candidato.nome)
        stmt.setString(2, candidato.sobrenome)
        stmt.setString(3, candidato.email)
        stmt.setDate(4, java.sql.Date.valueOf(candidato.dataNascimento))
        stmt.setString(5, candidato.pais)
        stmt.setString(6, candidato.cep)
        stmt.setString(7, candidato.descricao)
        stmt.setString(8, candidato.cpf)

        stmt.executeUpdate()
        stmt.close()
        conn.close()
        println("Candidato atualizado com sucesso.")
    }

    void deletarCandidato(String cpf) {
        def conn = DatabaseConnection.getConnection()
        def sql = "DELETE FROM candidatos WHERE cpf = ?"
        def stmt = conn.prepareStatement(sql)
        stmt.setString(1, cpf)

        stmt.executeUpdate()
        stmt.close()
        conn.close()
        println("Candidato deletado com sucesso.")
    }
}



