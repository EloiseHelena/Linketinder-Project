package controllers

import dao.CompetenciaDAO
import models.Competencia

class CompetenciaController {

    private final CompetenciaDAO competenciaDAO = new CompetenciaDAO()


    void inserirCompetencia(Competencia competencia) {
        try {
            competenciaDAO.inserirCompetencia(competencia)
        } catch (Exception e) {
            println("Erro ao inserir competência: " + e.message)
        }
    }


    Competencia buscarCompetenciaPorNome(String nome) {
        try {
            return competenciaDAO.buscarCompetenciaPorNome(nome)
        } catch (Exception e) {
            println("Erro ao buscar competência: " + e.message)
            return null
        }
    }


    void atualizarCompetencia(Competencia competencia) {
        try {
            competenciaDAO.atualizarCompetencia(competencia)
        } catch (Exception e) {
            println("Erro ao atualizar competência: " + e.message)
        }
    }

    void deletarCompetencia(String nome) {
        try {
            competenciaDAO.deletarCompetencia(nome)
        } catch (Exception e) {
            println("Erro ao deletar competência: " + e.message)
        }
    }
}
