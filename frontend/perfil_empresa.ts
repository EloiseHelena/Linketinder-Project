
document.addEventListener('DOMContentLoaded', () => {
  const empresaLogada = JSON.parse(localStorage.getItem('empresaLogada') || 'null');

  if (empresaLogada) {
      (document.getElementById('nome') as HTMLSpanElement).innerText = empresaLogada.nome || 'Nome não encontrado';
      (document.getElementById('email') as HTMLSpanElement).innerText = empresaLogada.email || 'Email não encontrado';
      (document.getElementById('descricao') as HTMLSpanElement).innerText = empresaLogada.descricao || 'Descrição não encontrada';
      (document.getElementById('cnpj') as HTMLSpanElement).innerText = empresaLogada.cnpj || 'CNPJ não encontrado';
      (document.getElementById('endereco') as HTMLSpanElement).innerText = empresaLogada.endereco || 'Endereço não encontrado';
      
      const competencias = Array.isArray(empresaLogada.competencias) ? 
      empresaLogada.competencias.join(', ') : 
      'Competências não encontradas';
      (document.getElementById('competencias') as HTMLSpanElement).innerText = competencias;
  } else {
      alert('Nenhum perfil encontrado. Por favor, faça login ou cadastre-se.');
      window.location.href = '/frontend/public/index.html';  
  }
});


const btnEditar = document.getElementById('editarPerfil');
btnEditar?.addEventListener('click', () => {
  window.location.href = '/frontend/public/editar_empresa.html';
});


const btnDeletar = document.getElementById('deletarPerfil');
btnDeletar?.addEventListener('click', () => {
  const confirmacao = confirm('Tem certeza que deseja deletar o perfil da empresa?');
  if (confirmacao) {
      localStorage.removeItem('empresaLogada');
      alert('Perfil deletado com sucesso!');
      window.location.href = '/frontend/public/index.html';
  }
});
