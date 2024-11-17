package dao
import db.DatabaseConnection
import vagas.Vaga
import java.sql.SQLException

class VagaDAO {
    void inserirVaga(Vaga vaga) {
        def conn = DatabaseConnection.getConnection()
        def stmt = null
        try {
            String sql = "INSERT INTO vagas (titulo, descricao, salario, requisitos, empresa_cnpj) VALUES (?, ?, ?, ?, ?)"
            stmt = conn.prepareStatement(sql)

            stmt.setString(1, vaga.titulo)
            stmt.setString(2, vaga.descricao)
            stmt.setBigDecimal(3, vaga.salario)
            stmt.setString(4, vaga.requisitos)
            stmt.setString(5, vaga.empresaCnpj)

            int linhasAfetadas = stmt.executeUpdate()
            if (linhasAfetadas > 0) {
                println("Vaga inserida com sucesso.")
            }
        } catch (SQLException e) {
            println("Erro ao inserir vaga: " + e.message)
        } finally {
            if (stmt != null) stmt.close()
            if (conn != null) conn.close()
        }
    }

    Vaga buscarVagaPorTitulo(String titulo) {
        def conn = DatabaseConnection.getConnection()
        def stmt = null
        Vaga vaga = null
        try {
            def sql = "SELECT * FROM vagas WHERE titulo = ?"
            stmt = conn.prepareStatement(sql)
            stmt.setString(1, titulo)

            def rs = stmt.executeQuery()
            if (rs.next()) {
                vaga = new Vaga(
                        rs.getString("titulo"),
                        rs.getString("descricao"),
                        rs.getBigDecimal("salario"),
                        rs.getString("requisitos"),
                        rs.getString("empresa_cnpj")
                )
            }
            rs.close()
        } catch (SQLException e) {
            println("Erro ao buscar vaga: " + e.message)
        } finally {
            if (stmt != null) stmt.close()
            if (conn != null) conn.close()
        }
        return vaga
    }

    void atualizarVaga(Vaga vaga) {
        def conn = DatabaseConnection.getConnection()
        def stmt = null
        try {
            def sql = "UPDATE vagas SET descricao = ?, salario = ?, requisitos = ?, empresa_cnpj = ? WHERE titulo = ?"
            stmt = conn.prepareStatement(sql)

            stmt.setString(1, vaga.descricao)
            stmt.setBigDecimal(2, vaga.salario)
            stmt.setString(3, vaga.requisitos)
            stmt.setString(4, vaga.empresaCnpj)
            stmt.setString(5, vaga.titulo)

            int linhasAfetadas = stmt.executeUpdate()
            if (linhasAfetadas > 0) {
                println("Vaga atualizada com sucesso.")
            }
        } catch (SQLException e) {
            println("Erro ao atualizar vaga: " + e.message)
        } finally {
            if (stmt != null) stmt.close()
            if (conn != null) conn.close()
        }
    }

    void deletarVaga(String titulo) {
        def conn = DatabaseConnection.getConnection()
        def stmt = null
        try {
            def sql = "DELETE FROM vagas WHERE titulo = ?"
            stmt = conn.prepareStatement(sql)
            stmt.setString(1, titulo)

            int linhasAfetadas = stmt.executeUpdate()
            if (linhasAfetadas > 0) {
                println("Vaga deletada com sucesso.")
            }
        } catch (SQLException e) {
            println("Erro ao deletar vaga: " + e.message)
        } finally {
            if (stmt != null) stmt.close()
            if (conn != null) conn.close()
        }
    }
}


