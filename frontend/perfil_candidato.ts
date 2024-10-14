document.addEventListener('DOMContentLoaded', () => {
  const candidatoLogado = JSON.parse(localStorage.getItem('candidatoLogado') || '{}');

  if (candidatoLogado) {
    (document.getElementById('nome') as HTMLSpanElement).innerText = candidatoLogado.nome || 'Nome não encontrado';
    (document.getElementById('email') as HTMLSpanElement).innerText = candidatoLogado.email || 'Email não encontrado';
    (document.getElementById('descricao') as HTMLSpanElement).innerText = candidatoLogado.descricao || 'Descrição não encontrada';
    (document.getElementById('cpf') as HTMLSpanElement).innerText = candidatoLogado.cpf || 'CPF não encontrado';
    (document.getElementById('dataNascimento') as HTMLSpanElement).innerText = candidatoLogado.dataNascimento || 'Data de Nascimento não encontrada';
    (document.getElementById('endereco') as HTMLSpanElement).innerText = candidatoLogado.endereco || 'Endereço não encontrado';
    
    const competencias = Array.isArray(candidatoLogado.competencias) ? 
    candidatoLogado.competencias.join(', ') : 
    'Competências não encontradas';
    (document.getElementById('competencias') as HTMLSpanElement).innerText = competencias;
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
  } else {
    console.error("Botão 'Excluir Perfil' não encontrado.");
  }

  const editarPerfilButton = document.getElementById('editarPerfil');
  if (editarPerfilButton) {
    editarPerfilButton.addEventListener('click', () => {
      window.location.href = '/frontend/public/editar_candidato.html';
    });
  } else {
    console.error("Botão 'Editar Perfil' não encontrado.");
  }
});
