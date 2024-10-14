
document.addEventListener('DOMContentLoaded', () => {
  const candidatoLogado = JSON.parse(localStorage.getItem('candidatoLogado') || '{}');

  if (candidatoLogado) {
      (document.getElementById('nome') as HTMLInputElement).value = candidatoLogado.nome;
      (document.getElementById('email') as HTMLInputElement).value = candidatoLogado.email;
      (document.getElementById('competencias') as HTMLInputElement).value = candidatoLogado.competencias.join(', ');
      (document.getElementById('descricao') as HTMLTextAreaElement).value = candidatoLogado.descricao;
      (document.getElementById('dataNascimento') as HTMLInputElement).value = candidatoLogado.dataNascimento;
      (document.getElementById('endereco') as HTMLInputElement).value = candidatoLogado.endereco;
  }
});

function salvarEdicaoCandidato() {
  const nome = (document.getElementById('nome') as HTMLInputElement).value;
  const email = (document.getElementById('email') as HTMLInputElement).value;
  const competencias = (document.getElementById('competencias') as HTMLInputElement).value.split(',').map(c => c.trim());
  const descricao = (document.getElementById('descricao') as HTMLTextAreaElement).value;
  const dataNascimento = (document.getElementById('dataNascimento') as HTMLInputElement).value;
  const endereco = (document.getElementById('endereco') as HTMLInputElement).value;

  const candidatoAtualizado = {
      ...JSON.parse(localStorage.getItem('candidatoLogado') || '{}'),
      nome,
      email,
      competencias,
      descricao,
      dataNascimento,
      endereco,
  };

  localStorage.setItem('candidatoLogado', JSON.stringify(candidatoAtualizado));
  alert('Perfil atualizado com sucesso!');
  window.location.href = '/frontend/public/perfil_candidato.html'; 
}
