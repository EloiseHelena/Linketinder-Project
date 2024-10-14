"use strict";
document.addEventListener('DOMContentLoaded', () => {
    const candidatoLogado = JSON.parse(localStorage.getItem('candidatoLogado') || '{}');
    if (candidatoLogado) {
        document.getElementById('nome').value = candidatoLogado.nome;
        document.getElementById('email').value = candidatoLogado.email;
        document.getElementById('competencias').value = candidatoLogado.competencias.join(', ');
        document.getElementById('descricao').value = candidatoLogado.descricao;
        document.getElementById('dataNascimento').value = candidatoLogado.dataNascimento;
        document.getElementById('endereco').value = candidatoLogado.endereco;
    }
});
function salvarEdicaoCandidato() {
    const nome = document.getElementById('nome').value;
    const email = document.getElementById('email').value;
    const competencias = document.getElementById('competencias').value.split(',').map(c => c.trim());
    const descricao = document.getElementById('descricao').value;
    const dataNascimento = document.getElementById('dataNascimento').value;
    const endereco = document.getElementById('endereco').value;
    const candidatoAtualizado = Object.assign(Object.assign({}, JSON.parse(localStorage.getItem('candidatoLogado') || '{}')), { nome,
        email,
        competencias,
        descricao,
        dataNascimento,
        endereco });
    localStorage.setItem('candidatoLogado', JSON.stringify(candidatoAtualizado));
    alert('Perfil atualizado com sucesso!');
    window.location.href = '/frontend/public/perfil_candidato.html';
}
