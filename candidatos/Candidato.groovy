package candidatos
import pessoas.Pessoa

public class Candidato extends Pessoa {
    private String cpf;
    private int idade;
    private String estado;
    private String cep;

    public Candidato(String nome, String email, String descricao, String[] competencias,
                     String cpf, int idade, String estado, String cep) {
        super(nome, email, descricao, competencias);
        this.cpf = cpf;
        this.idade = idade;
        this.estado = estado;
        this.cep = cep;
    }

    public String getCpf() {
        return cpf;
    }

    public int getIdade() {
        return idade;
    }

    public String getEstado() {
        return estado;
    }

    public String getCep() {
        return cep;
    }
}
