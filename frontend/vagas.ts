export interface Vaga {
  titulo: string;
  descricao: string;
  competenciasNecessarias: string[];
  anonimo: boolean;
  nomeEmpresa?: string;
}

export function carregarVagas(): Vaga[] {
  return JSON.parse(localStorage.getItem('vagas') || '[]');
}


document.addEventListener('DOMContentLoaded', () => {
  const listaVagasDiv = document.getElementById('listaVagas')!;

  const vagas = carregarVagas();
  if (vagas.length === 0) {
    listaVagasDiv.innerHTML = '<p>Não há vagas disponíveis.</p>';
    return;
  }

  vagas.forEach((vaga) => {
    const vagaDiv = document.createElement('div');
    vagaDiv.classList.add('vaga-item');
    vagaDiv.innerHTML = `
      <h3>${vaga.titulo}</h3>
      <p>${vaga.descricao}</p>
      <p>Competências: ${vaga.competenciasNecessarias.join(', ')}</p>
      <p>${vaga.anonimo ? 'Empresa: Anônimo' : `Empresa: ${vaga.nomeEmpresa}`}</p>
    `;
    listaVagasDiv.appendChild(vagaDiv);
  });
});


  