"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
document.addEventListener('DOMContentLoaded', () => {
    const cadastroForm = document.getElementById('cadastroCandidatoForm');
    cadastroForm.addEventListener('submit', (event) => __awaiter(void 0, void 0, void 0, function* () {
        event.preventDefault();
        const nome = document.getElementById('nome').value;
        const sobrenome = document.getElementById('sobrenome').value;
        const dataNascimento = document.getElementById('dataNascimento').value;
        const email = document.getElementById('email').value;
        const cpf = document.getElementById('cpf').value;
        const pais = document.getElementById('pais').value;
        const cep = document.getElementById('cep').value;
        const descricao = document.getElementById('descricao').value;
        const senha = document.getElementById('senha').value;
        try {
            const response = yield fetch('/api/cadastro/candidato', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ nome, sobrenome, dataNascimento, email, cpf, pais, cep, descricao, senha }),
            });
            const result = yield response.json();
            if (result.status === 'sucesso') {
                alert('Cadastro realizado com sucesso!');
                window.location.href = '/frontend/public/login.html';
            }
            else {
                alert(result.message);
            }
        }
        catch (error) {
            alert('Erro ao tentar realizar o cadastro.');
            console.error('Erro:', error);
        }
    }));
});
