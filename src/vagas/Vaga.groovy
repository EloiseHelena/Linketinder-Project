package vagas

class Vaga {
    String titulo
    String descricao
    BigDecimal salario
    String requisitos
    String empresaCnpj

    Vaga(String titulo, String descricao, BigDecimal salario, String requisitos, String empresaCnpj) {
        this.titulo = titulo
        this.descricao = descricao
        this.salario = salario
        this.requisitos = requisitos
        this.empresaCnpj = empresaCnpj
    }

    String toString() {
        return "${titulo} - ${descricao} - ${salario} - ${requisitos} - ${empresaCnpj}"
    }
}
