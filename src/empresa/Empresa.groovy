package empresa
import pessoas.Pessoa

class Empresa extends Pessoa {
    String cnpj
    String pais
    String estado
    String cep

    public Empresa(String nome, String email, String descricao, List<String> competencias,
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

    @Override
    public String toString() {
        return "Empresa:\n" +
                "nome= " + nome + '\n' +
                "email= " + email + '\n' +
                "descricao= " + descricao + '\n' +
                "cnpj= " + cnpj + '\n' +
                "pais= " + pais + '\n' +
                "cep= " + cep + '\n'
    }

}


