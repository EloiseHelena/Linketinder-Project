"use strict";
function validarCPF(cpf) {
    const cpfLimpo = cpf.replace(/\D/g, '');
    return cpfLimpo.length === 11;
}
function cpfJaExiste(cpf) {
    const candidatos = JSON.parse(localStorage.getItem('candidatos') || '[]');
    return candidatos.some((candidato) => candidato.cpf === cpf);
}
const formCandidato = document.getElementById('cadastroCandidatoForm');
formCandidato.addEventListener('submit', function (event) {
    event.preventDefault();
    const nome = document.getElementById('nome').value;
    const cpf = document.getElementById('cpf').value;
    const email = document.getElementById('email').value;
    const senha = document.getElementById('senha').value;
    const descricao = document.getElementById('descricao').value;
    const competencias = document.getElementById('competencias').value
        .split(',').map(c => c.trim());
    const dataNascimento = document.getElementById('dataNascimento').value;
    const endereco = document.getElementById('endereco').value;
    if (!validarCPF(cpf)) {
        alert('CPF inválido. Deve conter exatamente 11 dígitos.');
        return;
    }
    if (cpfJaExiste(cpf)) {
        alert('CPF já cadastrado!');
        return;
    }
    const candidato = { nome, cpf, email, senha, descricao, competencias, dataNascimento, endereco };
    const candidatos = JSON.parse(localStorage.getItem('candidatos') || '[]');
    candidatos.push(candidato);
    localStorage.setItem('candidatos', JSON.stringify(candidatos));
    alert('Candidato cadastrado com sucesso!');
    window.location.href = '/frontend/public/index.html';
});
