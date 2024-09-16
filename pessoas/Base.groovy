package pessoas

interface Base {
    public interface Pessoa {
        String getNome();
        String getEmail();
        String getCpf();
        String getCnpj();
        String getIdade();
        String getEstado();
        String getPais();
        String getCep();
        String getDescricao();
        String[] getCompetencias();
    }
}