class Empresa implements Pessoa {
    String nome
    String email
    String descricao
    List<String> competencias
    String cnpj
    String endereco

    Empresa(String nome, String email, String descricao, List<String> competencias,
            String cnpj, String endereco) {
        this.nome = nome
        this.email = email
        this.descricao = descricao
        this.competencias = competencias
        this.cnpj = cnpj
        this.endereco = endereco
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
