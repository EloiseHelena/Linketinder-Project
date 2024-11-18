package dao

import db.DatabaseConnection
import empresa.Empresa

import java.sql.SQLException

class EmpresaDAO extends BaseDAO {

    EmpresaDAO(DatabaseConnection databaseConnection) {
        super(databaseConnection)
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
        } catch (SQLException e) {
            e.printStackTrace()
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
                empresa = new Empresa(
                        rs.getString("nome_empresa"),
                        rs.getString("cnpj"),
                        rs.getString("email"),
                        rs.getString("pais"),
                        rs.getString("cep"),
                        rs.getString("descricao")
                )
            }
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            closeResources(conn, stmt, rs)
        }
        return empresa
    }
}

