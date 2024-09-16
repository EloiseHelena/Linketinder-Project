package pessoas

interface Base {
    public interface Pessoa {
        String getNome();
        String getEmail();
        String getDescricao();
        String[] getCompetencias();
    }
}