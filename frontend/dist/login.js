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
    const loginForm = document.getElementById('loginForm');
    loginForm.addEventListener('submit', (event) => __awaiter(void 0, void 0, void 0, function* () {
        event.preventDefault();
        const email = document.getElementById('email').value;
        const senha = document.getElementById('senha').value;
        try {
            const response = yield fetch('/api/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ email, senha }),
            });
            const result = yield response.json();
            if (result.status === 'sucesso') {
                if (result.tipo === 'empresa') {
                    alert('Login realizado com sucesso! Empresa logada.');
                    localStorage.setItem('empresaLogada', JSON.stringify(result.dados));
                    window.location.href = '/frontend/public/perfil_empresa.html';
                }
                else if (result.tipo === 'candidato') {
                    alert('Login realizado com sucesso! Candidato logado.');
                    localStorage.setItem('candidatoLogado', JSON.stringify(result.dados));
                    window.location.href = '/frontend/public/perfil_candidato.html';
                }
            }
            else {
                alert(result.message);
            }
        }
        catch (error) {
            alert('Erro ao tentar realizar o login.');
            console.error('Erro:', error);
        }
    }));
});
