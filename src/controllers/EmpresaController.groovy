package controllers

import dao.EmpresaDAO
import models.Empresa

class EmpresaController {

    private final EmpresaDAO empresaDAO = new EmpresaDAO()


    void inserirEmpresa(Empresa empresa) {
        try {
            empresaDAO.inserirEmpresa(empresa)
        } catch (Exception e) {
            println("Erro ao inserir empresa: " + e.message)
        }
    }

    Empresa buscarEmpresaPorCnpj(String cnpj) {
        try {
            return empresaDAO.buscarEmpresaPorCnpj(cnpj)
        } catch (Exception e) {
            println("Erro ao buscar empresa: " + e.message)
            return null
        }
    }

    void atualizarEmpresa(Empresa empresa) {
        try {
            empresaDAO.atualizarEmpresa(empresa)
        } catch (Exception e) {
            println("Erro ao atualizar empresa: " + e.message)
        }
    }

    void deletarEmpresa(String cnpj) {
        try {
            empresaDAO.deletarEmpresa(cnpj)
        } catch (Exception e) {
            println("Erro ao deletar empresa: " + e.message)
        }
    }
}
