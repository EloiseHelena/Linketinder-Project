class Candidato implements Pessoa {
    String nome
    String email
    String descricao
    List<String> competencias
    String cpf
    int idade
    String estado
    String cep

    Candidato(String nome, String email, String descricao, List<String> competencias,
              String cpf, int idade, String estado, String cep) {
        this.nome = nome
        this.email = email
        this.descricao = descricao
        this.competencias = competencias
        this.cpf = cpf
        this.idade = idade
        this.estado = estado
        this.cep = cep
    }

    @Override
    String getNome() {
        return nome
    }

    @Override
    String getEmail() {
        return email
    }

    @Override
    String getDescricao() {
        return descricao
    }

    @Override
    List<String> getCompetencias() {
        return competencias
    }
}
