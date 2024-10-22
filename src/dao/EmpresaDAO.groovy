package dao
import db.DatabaseConnection
import empresa.Empresa
import java.sql.SQLException

class EmpresaDAO {

    void inserirEmpresa(Empresa empresa) {
        def conn = DatabaseConnection.getConnection()
        def stmt = null
        try {
            def sql = "INSERT INTO empresas (nome, cnpj, email, pais, cep, descricao) VALUES (?, ?, ?, ?, ?, ?)"
            stmt = conn.prepareStatement(sql)

            stmt.setString(1, empresa.nome)
            stmt.setString(2, empresa.cnpj)
            stmt.setString(3, empresa.email)
            stmt.setString(4, empresa.pais)
            stmt.setString(5, empresa.cep)
            stmt.setString(6, empresa.descricao)

            int linhasAfetadas = stmt.executeUpdate()
            if (linhasAfetadas > 0) {
                println("Empresa inserida com sucesso.")
            }
        } catch (SQLException e) {
            println("Erro ao inserir empresa: " + e.message)
        } finally {
            if (stmt != null) stmt.close()
            if (conn != null) conn.close()
        }
    }

    Empresa buscarEmpresaPorCnpj(String cnpj) {
        def conn = DatabaseConnection.getConnection()
        def stmt = null
        Empresa empresa = null
        try {
            def sql = "SELECT * FROM empresas WHERE cnpj = ?"
            stmt = conn.prepareStatement(sql)
            stmt.setString(1, cnpj)

            def rs = stmt.executeQuery()
            if (rs.next()) {
                empresa = new Empresa(
                        rs.getString("nome"),
                        rs.getString("cnpj"),
                        rs.getString("email"),
                        rs.getString("pais"),
                        rs.getString("cep"),
                        rs.getString("descricao")
                )
            }
            rs.close()
        } catch (SQLException e) {
            println("Erro ao buscar empresa: " + e.message)
        } finally {
            if (stmt != null) stmt.close()
            if (conn != null) conn.close()
        }
        return empresa
    }

    void atualizarEmpresa(Empresa empresa) {
        def conn = DatabaseConnection.getConnection()
        def stmt = null
        try {
            def sql = "UPDATE empresas SET nome = ?, email = ?, pais = ?, cep = ?, descricao = ? WHERE cnpj = ?"
            stmt = conn.prepareStatement(sql)

            stmt.setString(1, empresa.nome)
            stmt.setString(2, empresa.email)
            stmt.setString(3, empresa.pais)
            stmt.setString(4, empresa.cep)
            stmt.setString(5, empresa.descricao)
            stmt.setString(6, empresa.cnpj)

            int linhasAfetadas = stmt.executeUpdate()
            if (linhasAfetadas > 0) {
                println("Empresa atualizada com sucesso.")
            }
        } catch (SQLException e) {
            println("Erro ao atualizar empresa: " + e.message)
        } finally {
            if (stmt != null) stmt.close()
            if (conn != null) conn.close()
        }
    }

    void deletarEmpresa(String cnpj) {
        def conn = DatabaseConnection.getConnection()
        def stmt = null
        try {
            def sql = "DELETE FROM empresas WHERE cnpj = ?"
            stmt = conn.prepareStatement(sql)
            stmt.setString(1, cnpj)

            int linhasAfetadas = stmt.executeUpdate()
            if (linhasAfetadas > 0) {
                println("Empresa deletada com sucesso.")
            }
        } catch (SQLException e) {
            println("Erro ao deletar empresa: " + e.message)
        } finally {
            if (stmt != null) stmt.close()
            if (conn != null) conn.close()
        }
    }
}
