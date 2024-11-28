package controller

import dao.CandidatoDAO
import models.Candidato

class CandidatoController {

    private final CandidatoDAO candidatoDAO = new CandidatoDAO()

    void cadastrarCandidato(Candidato candidato) {
        try {
            candidatoDAO.cadastrarCandidato(candidato)
        } catch (Exception e) {
            println("Erro ao inserir candidato: " + e.message)
        }
    }


    Candidato buscarCandidatoPorCpf(String cpf) {
        try {
            return candidatoDAO.buscarCandidatoPorCpf(cpf)
        } catch (Exception e) {
            println("Erro ao buscar candidato: " + e.message)
            return null
        }
    }


    void atualizarCandidato(Candidato candidato) {
        try {
            candidatoDAO.atualizarCandidato(candidato)
        } catch (Exception e) {
            println("Erro ao atualizar candidato: " + e.message)
        }
    }

    void deletarCandidato(String cpf) {
        try {
            candidatoDAO.deletarCandidato(cpf)
        } catch (Exception e) {
            println("Erro ao deletar candidato: " + e.message)
        }
    }
}
