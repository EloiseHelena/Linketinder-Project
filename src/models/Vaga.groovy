package models

import javax.persistence.*
import javax.validation.constraints.*

@Entity
class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @NotNull
    @Size(min = 3, max = 100)
    String titulo

    @NotNull
    @Size(min = 10, max = 1000)
    String descricao

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true, message = "Salário deve ser maior ou igual a 0")
    BigDecimal salario

    @NotNull
    @Size(min = 10, max = 1000)
    String requisitos

    @NotNull
    @Pattern(regexp = "\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}", message = "CNPJ inválido")
    String empresaCnpj

    // Construtor
    Vaga(String titulo, String descricao, BigDecimal salario, String requisitos, String empresaCnpj) {
        this.titulo = titulo
        this.descricao = descricao
        this.salario = salario
        this.requisitos = requisitos
        this.empresaCnpj = empresaCnpj
    }

    // Método toString
    @Override
    String toString() {
        return "${titulo} - ${descricao} - ${salario} - ${requisitos} - ${empresaCnpj}"
    }
}
