"use strict";
document.addEventListener('DOMContentLoaded', () => {
    const candidatoLogado = JSON.parse(localStorage.getItem('candidatoLogado') || '{}');
    if (candidatoLogado) {
        document.getElementById('nome').innerText = candidatoLogado.nome || 'Nome não encontrado';
        document.getElementById('email').innerText = candidatoLogado.email || 'Email não encontrado';
        document.getElementById('descricao').innerText = candidatoLogado.descricao || 'Descrição não encontrada';
        document.getElementById('cpf').innerText = candidatoLogado.cpf || 'CPF não encontrado';
        document.getElementById('dataNascimento').innerText = candidatoLogado.dataNascimento || 'Data de Nascimento não encontrada';
        document.getElementById('endereco').innerText = candidatoLogado.endereco || 'Endereço não encontrado';
        const competencias = Array.isArray(candidatoLogado.competencias) ?
            candidatoLogado.competencias.join(', ') :
            'Competências não encontradas';
        document.getElementById('competencias').innerText = competencias;
    }
    const deletarPerfilButton = document.getElementById('deletarPerfil');
    if (deletarPerfilButton) {
        deletarPerfilButton.addEventListener('click', () => {
            if (confirm('Você tem certeza que deseja excluir seu perfil?')) {
                localStorage.removeItem('candidatoLogado');
                alert('Perfil excluído com sucesso!');
                window.location.href = '/frontend/public/index.html';
            }
        });
    }
    else {
        console.error("Botão 'Excluir Perfil' não encontrado.");
    }
    const editarPerfilButton = document.getElementById('editarPerfil');
    if (editarPerfilButton) {
        editarPerfilButton.addEventListener('click', () => {
            window.location.href = '/frontend/public/editar_candidato.html';
        });
    }
    else {
        console.error("Botão 'Editar Perfil' não encontrado.");
    }
});
