package controllers

import dao.VagaDAO
import models.Vaga

class VagaController {

    private final VagaDAO vagaDAO = new VagaDAO()

    void inserirVaga(Vaga vaga) {
        try {
            vagaDAO.inserirVaga(vaga)
        } catch (Exception e) {
            println("Erro ao inserir vaga: " + e.message)
        }
    }

    Vaga buscarVagaPorTitulo(String titulo) {
        try {
            return vagaDAO.buscarVagaPorTitulo(titulo)
        } catch (Exception e) {
            println("Erro ao buscar vaga: " + e.message)
            return null
        }
    }

    void atualizarVaga(Vaga vaga) {
        try {
            vagaDAO.atualizarVaga(vaga)
        } catch (Exception e) {
            println("Erro ao atualizar vaga: " + e.message)
        }
    }

    void deletarVaga(String titulo) {
        try {
            vagaDAO.deletarVaga(titulo)
        } catch (Exception e) {
            println("Erro ao deletar vaga: " + e.message)
        }
    }
}
