package models.pessoas

interface Base {
    String getNome()
    String getEmail()
    String getDescricao()
    List<String> getCompetencias()


    default String exibirResumo() {
        return "Nome: ${getNome()}, Email: ${getEmail()}"
    }
}
