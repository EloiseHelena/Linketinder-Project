package dao

import dao.BaseDAO
import db.DatabaseConnection
import models.Empresa
import java.sql.ResultSet
import java.sql.SQLException

class EmpresaDAO extends BaseDAO {

    EmpresaDAO(DatabaseConnection databaseConnection) {
        super(databaseConnection)
    }


    @Override
    def mapResultSet(ResultSet rs) {
        return new Empresa(
                rs.getString("nome_empresa"),
                rs.getString("cnpj"),
                rs.getString("email"),
                rs.getString("pais"),
                rs.getString("cep"),
                rs.getString("descricao")
        )
    }

    void inserirEmpresa(Empresa empresa) {
        def conn = null
        def stmt = null
        try {
            conn = databaseConnection.getConnection()
            String sql = "INSERT INTO empresas (nome_empresa, cnpj, email, pais, cep, descricao) VALUES (?, ?, ?, ?, ?, ?)"
            stmt = conn.prepareStatement(sql)

            stmt.setString(1, empresa.nome)
            stmt.setString(2, empresa.cnpj)
            stmt.setString(3, empresa.email)
            stmt.setString(4, empresa.pais)
            stmt.setString(5, empresa.cep)
            stmt.setString(6, empresa.descricao)

            stmt.executeUpdate()
            println("Empresa inserida com sucesso.")
        } catch (SQLException e) {
            println("Erro ao inserir empresa: " + e.message)
        } finally {
            closeResources(conn, stmt, null)
        }
    }


    Empresa buscarEmpresaPorCnpj(String cnpj) {
        def conn = null
        def stmt = null
        def rs = null
        Empresa empresa = null
        try {
            conn = databaseConnection.getConnection()
            String sql = "SELECT * FROM empresas WHERE cnpj = ?"
            stmt = conn.prepareStatement(sql)
            stmt.setString(1, cnpj)

            rs = stmt.executeQuery()
            if (rs.next()) {
                empresa = mapResultSet(rs)
            }
        } catch (SQLException e) {
            println("Erro ao buscar empresa: " + e.message)
        } finally {
            closeResources(conn, stmt, rs)
        }
        return empresa
    }


    List<Empresa> listarEmpresas() {
        List<Empresa> empresas = []
        def conn = null
        def stmt = null
        def rs = null
        try {
            conn = databaseConnection.getConnection()
            String sql = "SELECT * FROM empresas"
            stmt = conn.prepareStatement(sql)
            rs = stmt.executeQuery()

            while (rs.next()) {
                empresas.add(mapResultSet(rs))
            }
        } catch (SQLException e) {
            println("Erro ao listar empresas: " + e.message)
        } finally {
            closeResources(conn, stmt, rs)
        }

        return empresas
    }


    void atualizarEmpresa(Empresa empresa) {
        def conn = null
        def stmt = null
        try {
            conn = databaseConnection.getConnection()
            String sql = "UPDATE empresas SET nome_empresa = ?, email = ?, pais = ?, cep = ?, descricao = ? WHERE cnpj = ?"
            stmt = conn.prepareStatement(sql)

            stmt.setString(1, empresa.nome)
            stmt.setString(2, empresa.email)
            stmt.setString(3, empresa.pais)
            stmt.setString(4, empresa.cep)
            stmt.setString(5, empresa.descricao)
            stmt.setString(6, empresa.cnpj)

            stmt.executeUpdate()
            println("Empresa atualizada com sucesso.")
        } catch (SQLException e) {
            println("Erro ao atualizar empresa: " + e.message)
        } finally {
            closeResources(conn, stmt, null)
        }
    }


    void deletarEmpresa(String cnpj) {
        def conn = null
        def stmt = null
        try {
            conn = databaseConnection.getConnection()
            String sql = "DELETE FROM empresas WHERE cnpj = ?"
            stmt = conn.prepareStatement(sql)
            stmt.setString(1, cnpj)

            stmt.executeUpdate()
            println("Empresa deletada com sucesso.")
        } catch (SQLException e) {
            println("Erro ao deletar empresa: " + e.message)
        } finally {
            closeResources(conn, stmt, null)
        }
    }
}
