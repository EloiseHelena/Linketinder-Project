package pessoas

public abstract class Pessoa implements Base {
    protected String nome;
    protected String email;
    protected String cpf;
    protected String cnpj;
    protected String idade;
    protected String estado;
    protected String pais;
    protected String cep;
    protected String descricao;
    protected String[] competencias;

    public Pessoa(String nome, String email, String cpf, String cnpj, String idade, String estado, String pais, String cep,String descricao, String[] competencias) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.idade = idade;
        this.estado = estado;
        this.pais = pais;
        this.cep = cep;
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
    public String getCpf() {
        return cpf;
    }

    @Override
    public String getCnpj() {
        return cnpj;
    }

    @Override
    public String getIdade() {
        return idade;
    }

    @Override
    public String getEstado() {
        return estado;
    }

    @Override
    public String getPais() {
        return pais;
    }

    @Override
    public String getCep() {
        return cep;
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

