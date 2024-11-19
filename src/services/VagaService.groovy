package services

import dao.VagaDAO
import models.Vaga

class VagaService {

    private final VagaDAO vagaDAO = new VagaDAO()

    void inserirVaga(Vaga vaga) {
        if (vaga.titulo == null || vaga.titulo.isEmpty()) {
            throw new IllegalArgumentException("Título da vaga é obrigatório.")
        }
        vagaDAO.inserirVaga(vaga)
    }

    Vaga buscarVagaPorTitulo(String titulo) {
        if (titulo == null || titulo.isEmpty()) {
            throw new IllegalArgumentException("Título da vaga é obrigatório.")
        }
        return vagaDAO.buscarVagaPorTitulo(titulo)
    }

    void atualizarVaga(Vaga vaga) {
        if (vaga.titulo == null || vaga.titulo.isEmpty()) {
            throw new IllegalArgumentException("Título da vaga é obrigatório.")
        }
        vagaDAO.atualizarVaga(vaga)
    }

    void deletarVaga(String titulo) {
        if (titulo == null || titulo.isEmpty()) {
            throw new IllegalArgumentException("Título da vaga é obrigatório.")
        }
        vagaDAO.deletarVaga(titulo)
    }
}
