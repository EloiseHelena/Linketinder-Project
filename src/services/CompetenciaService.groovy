package services

import dao.CompetenciaDAO
import models.Competencia

class CompetenciaService {

    private final CompetenciaDAO competenciaDAO = new CompetenciaDAO()

    void inserirCompetencia(Competencia competencia) {
        if (competencia.nome == null || competencia.descricao == null) {
            throw new IllegalArgumentException("Nome e Descrição são obrigatórios.")
        }
        competenciaDAO.inserirCompetencia(competencia)
    }

    Competencia buscarCompetenciaPorNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome da competência é obrigatório.")
        }
        return competenciaDAO.buscarCompetenciaPorNome(nome)
    }

    void atualizarCompetencia(Competencia competencia) {
        if (competencia.nome == null || competencia.descricao == null) {
            throw new IllegalArgumentException("Nome e Descrição são obrigatórios.")
        }
        competenciaDAO.atualizarCompetencia(competencia)
    }

    void deletarCompetencia(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome da competência é obrigatório.")
        }
        competenciaDAO.deletarCompetencia(nome)
    }
}
