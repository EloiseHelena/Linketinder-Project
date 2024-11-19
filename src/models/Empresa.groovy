package models

import models.pessoas.Pessoa
import javax.persistence.*
import javax.validation.constraints.*

@Entity
class Empresa extends Pessoa {

    @NotNull
    @Pattern(regexp = "\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}", message = "CNPJ inválido")
    String cnpj

    @NotNull
    String pais

    @NotNull
    String estado

    @NotNull
    String cep

    public Empresa(String nome, String email, String descricao, List<String> competencias,
                   String cnpj, String pais, String estado, String cep) {
        super(nome, email, descricao, competencias)
        this.cnpj = cnpj
        this.pais = pais
        this.estado = estado
        this.cep = cep
    }

    String getCnpj() {
        return cnpj
    }

    String getPais() {
        return pais
    }

    String getEstado() {
        return estado
    }

    String getCep() {
        return cep
    }

    @Override
    public String toString() {
        return "Empresa:\n" +
                "nome= " + nome + '\n' +
                "email= " + email + '\n' +
                "descricao= " + descricao + '\n' +
                "cnpj= " + cnpj + '\n' +
                "pais= " + pais + '\n' +
                "estado= " + estado + '\n' +
                "cep= " + cep + '\n'
    }
}


