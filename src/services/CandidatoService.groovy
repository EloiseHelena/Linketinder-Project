package services

import dao.CandidatoDAO
import models.Candidato

class CandidatoService {

    private final CandidatoDAO candidatoDAO = new CandidatoDAO()

    void cadastrarCandidato(Candidato candidato) {
        if (candidato.cpf == null || candidato.nome == null || candidato.email == null) {
            throw new IllegalArgumentException("CPF, Nome e E-mail são obrigatórios.")
        }
        candidatoDAO.inserirCandidato(candidato)
    }

    Candidato buscarCandidatoPorCpf(String cpf) {
        if (cpf == null || cpf.isEmpty()) {
            throw new IllegalArgumentException("CPF é obrigatório.")
        }
        return candidatoDAO.buscarCandidatoPorCpf(cpf)
    }

    void atualizarCandidato(Candidato candidato) {
        if (candidato.cpf == null || candidato.nome == null || candidato.email == null) {
            throw new IllegalArgumentException("CPF, Nome e E-mail são obrigatórios.")
        }
        candidatoDAO.atualizarCandidato(candidato)
    }

    void deletarCandidato(String cpf) {
        if (cpf == null || cpf.isEmpty()) {
            throw new IllegalArgumentException("CPF é obrigatório.")
        }
        candidatoDAO.deletarCandidato(cpf)
    }
}
