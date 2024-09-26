package candidatos
import pessoas.Pessoa

class Candidato extends Pessoa {
    String cpf;
    int idade;
    String estado;
    String cep;

    Candidato(String nome, String email, String descricao, List<String> competencias,
                     String cpf, int idade, String estado, String cep) {
        super(nome, email, descricao, competencias);
        this.cpf = cpf;
        this.idade = idade;
        this.estado = estado;
        this.cep = cep;
    }

     String getCpf() {
        return cpf;
    }

    int getIdade() {
        return idade;
    }

    String getEstado() {
        return estado;
    }

    String getCep() {
        return cep;
    }


}





