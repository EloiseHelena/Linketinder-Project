"use strict";
function validarCPF(cpf) {
    const regexCPF = /^\d{11}$/;
    return regexCPF.test(cpf);
}
function validarEmail(email) {
    const regexEmail = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return regexEmail.test(email);
}
function validarSenha(senha) {
    const regexSenha = /^.{8,}$/;
    return regexSenha.test(senha);
}
const formCandidato = document.getElementById('cadastroCandidatoForm');
formCandidato.addEventListener('submit', (event) => {
    event.preventDefault();
    const nome = document.getElementById('nome').value;
    const cpf = document.getElementById('cpf').value;
    const email = document.getElementById('email').value;
    const dataNascimento = document.getElementById('dataNascimento').value;
    const endereco = document.getElementById('endereco').value;
    const descricao = document.getElementById('descricao').value;
    const competencias = document.getElementById('competencias').value.split(',');
    const senha = document.getElementById('senha').value;
    if (!validarCPF(cpf)) {
        alert('CPF inválido. Deve conter exatamente 11 dígitos.');
        return;
    }
    if (!validarEmail(email)) {
        alert('Email inválido.');
        return;
    }
    if (!validarSenha(senha)) {
        alert('A senha deve ter no mínimo 8 caracteres.');
        return;
    }
    const candidato = { nome, cpf, email, dataNascimento, endereco, descricao, competencias, senha };
    const candidatos = JSON.parse(localStorage.getItem('candidatos') || '[]');
    candidatos.push(candidato);
    localStorage.setItem('candidatos', JSON.stringify(candidatos));
    alert('Candidato cadastrado com sucesso!');
    window.location.href = '/frontend/public/index.html';
});
