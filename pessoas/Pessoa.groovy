package pessoas

public abstract class Pessoa implements Base {
    protected String nome;
    protected String email;
    protected String descricao;
    protected String[] competencias;

    public Pessoa(String nome, String email, String descricao, String[] competencias) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
        this.competencias = competencias;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public String[] getCompetencias() {
        return competencias;
    }
}

