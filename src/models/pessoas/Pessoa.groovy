package models.pessoas

abstract class Pessoa implements Base {
    String nome
    String email
    String descricao
    List<String> competencias

    public Pessoa() {}

    public Pessoa(String nome, String email, String descricao, List<String> competencias) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio")
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email não pode ser vazio")
        }
        this.nome = nome
        this.email = email
        this.descricao = descricao
        this.competencias = competencias ?: []
    }

    String getNome() {
        return nome
    }

    String getEmail() {
        return email
    }

    String getDescricao() {
        return descricao
    }

    List<String> getCompetencias() {
        return competencias
    }

    @Override
    String toString() {
        return "Pessoa: nome= ${nome}, email= ${email}, descricao= ${descricao}, competencias= ${competencias.join(', ')}"
    }
}
