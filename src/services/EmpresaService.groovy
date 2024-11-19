package services

import dao.EmpresaDAO
import models.Empresa

class EmpresaService {

    private final EmpresaDAO empresaDAO = new EmpresaDAO()

    void inserirEmpresa(Empresa empresa) {
        if (empresa.cnpj == null || empresa.nome == null) {
            throw new IllegalArgumentException("CNPJ e Nome são obrigatórios.")
        }
        empresaDAO.inserirEmpresa(empresa)
    }

    Empresa buscarEmpresaPorCnpj(String cnpj) {
        if (cnpj == null || cnpj.isEmpty()) {
            throw new IllegalArgumentException("CNPJ é obrigatório.")
        }
        return empresaDAO.buscarEmpresaPorCnpj(cnpj)
    }

    void atualizarEmpresa(Empresa empresa) {
        if (empresa.cnpj == null || empresa.nome == null) {
            throw new IllegalArgumentException("CNPJ e Nome são obrigatórios.")
        }
        empresaDAO.atualizarEmpresa(empresa)
    }

    void deletarEmpresa(String cnpj) {
        if (cnpj == null || cnpj.isEmpty()) {
            throw new IllegalArgumentException("CNPJ é obrigatório.")
        }
        empresaDAO.deletarEmpresa(cnpj)
    }
}
