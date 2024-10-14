"use strict";
function validarCNPJ(cnpj) {
    const cnpjLimpo = cnpj.replace(/\D/g, '');
    return cnpjLimpo.length === 14;
}
function cnpjJaExiste(cnpj) {
    const empresas = JSON.parse(localStorage.getItem('empresas') || '[]');
    return empresas.some((empresa) => empresa.cnpj === cnpj);
}
const formEmpresa = document.getElementById('cadastroEmpresaForm');
formEmpresa.addEventListener('submit', function (event) {
    event.preventDefault();
    const nome = document.getElementById('nome').value;
    const cnpj = document.getElementById('cnpj').value;
    const email = document.getElementById('email').value;
    const senha = document.getElementById('senha').value;
    const descricao = document.getElementById('descricao').value;
    const competencias = document.getElementById('competencias').value
        .split(',').map(c => c.trim());
    const endereco = document.getElementById('endereco').value;
    if (!validarCNPJ(cnpj)) {
        alert('CNPJ inválido. Deve conter exatamente 14 dígitos.');
        return;
    }
    if (cnpjJaExiste(cnpj)) {
        alert('CNPJ já cadastrado!');
        return;
    }
    const empresa = { nome, cnpj, email, senha, descricao, competencias, endereco };
    const empresas = JSON.parse(localStorage.getItem('empresas') || '[]');
    empresas.push(empresa);
    localStorage.setItem('empresas', JSON.stringify(empresas));
    alert('Empresa cadastrada com sucesso!');
    window.location.href = '/frontend/public/index.html';
});
