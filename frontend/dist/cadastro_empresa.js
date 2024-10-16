"use strict";
function validarCNPJ(cnpj) {
    const regexCNPJ = /^\d{14}$/;
    return regexCNPJ.test(cnpj);
}
const formEmpresa = document.getElementById('cadastroEmpresaForm');
formEmpresa.addEventListener('submit', (event) => {
    event.preventDefault();
    const nome = document.getElementById('nome').value;
    const cnpj = document.getElementById('cnpj').value;
    const email = document.getElementById('email').value;
    const endereco = document.getElementById('endereco').value;
    const descricao = document.getElementById('descricao').value;
    const competencias = document.getElementById('competencias').value.split(',');
    const senha = document.getElementById('senha').value;
    if (!validarCNPJ(cnpj)) {
        alert('CNPJ inválido. Deve conter exatamente 14 dígitos.');
        return;
    }
    const empresa = { nome, cnpj, email, endereco, descricao, competencias, senha };
    const empresas = JSON.parse(localStorage.getItem('empresas') || '[]');
    empresas.push(empresa);
    localStorage.setItem('empresas', JSON.stringify(empresas));
    alert('Empresa cadastrada com sucesso!');
    window.location.href = '/frontend/public/index.html';
});
