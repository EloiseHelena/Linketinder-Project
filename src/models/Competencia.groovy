package models

import javax.persistence.*
import javax.validation.constraints.*

@Entity
class Competencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @NotNull
    @Size(min = 3, max = 100)
    String nome

    @Size(max = 1000)
    String descricao

    Competencia(String nome, String descricao) {
        this.nome = nome
        this.descricao = descricao
    }

    @Override
    String toString() {
        return "${nome} - ${descricao}"
    }
}
