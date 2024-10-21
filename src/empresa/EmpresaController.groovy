package empresa
import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.SQLException

@RestController
class EmpresaController {

    @PostMapping("/api/cadastro/empresa")
    ResponseEntity<?> cadastrarEmpresa(@RequestBody Empresa empresa) {
        Connection connection = null
        try {
            connection = DatabaseConnection.getConnection()
            String sql = "INSERT INTO empresas (nome_empresa, cnpj, email, descricao, pais, cep, senha) VALUES (?, ?, ?, ?, ?, ?, ?)"
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
            preparedStatement.setString(1, empresa.nomeEmpresa)
            preparedStatement.setString(2, empresa.cnpj)
            preparedStatement.setString(3, empresa.email)
            preparedStatement.setString(4, empresa.descricao)
            preparedStatement.setString(5, empresa.pais)
            preparedStatement.setString(6, empresa.cep)
            preparedStatement.setString(7, empresa.senha)
            preparedStatement.executeUpdate()

            return new ResponseEntity<>("Empresa cadastrada com sucesso!", HttpStatus.CREATED)
        } catch (SQLException e) {
            return new ResponseEntity<>("Erro ao cadastrar empresa: ${e.message}", HttpStatus.INTERNAL_SERVER_ERROR)
        } finally {
            if (connection != null) {
                connection.close()
            }
        }
    }
}
