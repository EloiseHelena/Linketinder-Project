package pessoas

abstract class Pessoa implements Base {
    String nome;
    String email;
    String descricao;
    List<String> competencias

    Pessoa(String nome, String email, String descricao, List<String> competencias) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
        this.competencias = competencias;
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
}

