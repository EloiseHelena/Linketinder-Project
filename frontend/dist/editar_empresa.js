"use strict";
document.addEventListener('DOMContentLoaded', () => {
    const empresaLogada = JSON.parse(localStorage.getItem('empresaLogada') || 'null');
    if (empresaLogada) {
        document.getElementById('nome').value = empresaLogada.nome;
        document.getElementById('email').value = empresaLogada.email;
        document.getElementById('descricao').value = empresaLogada.descricao;
        document.getElementById('competencias').value = empresaLogada.competencias.join(', ');
        document.getElementById('endereco').value = empresaLogada.endereco;
    }
});
const formEditarEmpresa = document.getElementById('formEditarEmpresa');
formEditarEmpresa.addEventListener('submit', function (event) {
    event.preventDefault();
    const nome = document.getElementById('nome').value;
    const email = document.getElementById('email').value;
    const descricao = document.getElementById('descricao').value;
    const competencias = document.getElementById('competencias').value.split(',').map(c => c.trim());
    const endereco = document.getElementById('endereco').value;
    const empresaAtualizada = Object.assign(Object.assign({}, JSON.parse(localStorage.getItem('empresaLogada') || '{}')), { nome,
        email,
        descricao,
        competencias,
        endereco });
    localStorage.setItem('empresaLogada', JSON.stringify(empresaAtualizada));
    alert('Perfil da empresa atualizado com sucesso!');
    window.location.href = '/frontend/public/perfil_empresa.html';
});
