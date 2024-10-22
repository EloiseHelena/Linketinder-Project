package competencias

class Competencia {
    String nome
    String descricao

    Competencia(String nome, String descricao) {
        this.nome = nome
        this.descricao = descricao
    }

    String toString() {
        return "${nome} - ${descricao}"
    }
}
