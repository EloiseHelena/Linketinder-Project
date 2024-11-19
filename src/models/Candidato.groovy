package models

import javax.persistence.*
import javax.validation.constraints.*
import java.time.LocalDate

@Entity
class Candidato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @NotNull
    String nome

    @NotNull
    String sobrenome

    @NotNull
    @Past
    String dataNascimento

    @Email
    @NotNull
    String email

    @NotNull
    String cpf

    @NotNull
    String pais

    @NotNull
    String cep

    String descricao


    public Candidato(String nome, String sobrenome, String dataNascimento, String email, String cpf, String pais, String cep, String descricao) {
        this.nome = nome
        this.sobrenome = sobrenome
        this.dataNascimento = dataNascimento
        this.email = email
        this.cpf = cpf
        this.pais = pais
        this.cep = cep
        this.descricao = descricao
    }

    @Override
    public String toString() {
        return "Candidato:\n" +
                "nome= " + nome + '\n' +
                "sobrenome= " + sobrenome + '\n' +
                "dataNascimento= " + dataNascimento + '\n' +
                "email= " + email + '\n' +
                "cpf= " + cpf + '\n' +
                "pais= " + pais + '\n' +
                "cep= " + cep + '\n' +
                "descricao= " + descricao + '\n'
    }
}
