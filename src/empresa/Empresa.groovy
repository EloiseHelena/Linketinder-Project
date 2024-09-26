package empresa
import pessoas.Pessoa

class Empresa extends Pessoa {
    String cnpj
    String pais
    String estado
    String cep

    Empresa(String nome, String email, String descricao, List<String> competencias,
            String cnpj, String pais, String estado, String cep) {
        super(nome, email, descricao, competencias)
        this.cnpj = cnpj
        this.pais = pais
        this.estado = estado
        this.cep = cep
    }

    String getCnpj() {
        return cnpj
    }

    String getPais() {
        return pais
    }

    String getEstado() {
        return estado
    }

    String getCep() {
        return cep
    }

}


