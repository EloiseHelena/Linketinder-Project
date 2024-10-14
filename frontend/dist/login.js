"use strict";
document.addEventListener('DOMContentLoaded', () => {
    const loginForm = document.getElementById('loginForm');
    if (!loginForm) {
        console.error("Formulário de login não encontrado.");
        return;
    }
    loginForm.addEventListener('submit', function (event) {
        event.preventDefault();
        const email = document.getElementById('email').value;
        const senha = document.getElementById('password').value;
        if (!email || !senha) {
            alert('Por favor, preencha todos os campos.');
            return;
        }
        const candidatos = JSON.parse(localStorage.getItem('candidatos') || '[]');
        const empresas = JSON.parse(localStorage.getItem('empresas') || '[]');
        const usuarioCandidato = candidatos.find((c) => c.email === email && c.senha === senha);
        const usuarioEmpresa = empresas.find((e) => e.email === email && e.senha === senha);
        if (usuarioCandidato) {
            localStorage.setItem('candidatoLogado', JSON.stringify(usuarioCandidato));
            alert('Login realizado com sucesso!');
            window.location.href = 'perfil_candidato.html';
        }
        else if (usuarioEmpresa) {
            localStorage.setItem('empresaLogada', JSON.stringify(usuarioEmpresa));
            alert('Login realizado com sucesso!');
            window.location.href = 'perfil_empresa.html';
        }
        else {
            alert('Email ou senha incorretos.');
        }
    });
});
