import { Vaga, carregarVagas } from './vagas';
import { Candidato as CandidatoType, carregarCandidatos as carregar } from './candidatos';
import { Chart } from 'chart.js';

export interface Candidato {
  nome: string;
  competencias: string[];
  anonimo: boolean;
}

export function carregarCandidatos(): Candidato[] {
  return JSON.parse(localStorage.getItem('candidatos') || '[]');
}


function calcularAfinidade(candidato: Candidato, vaga: Vaga): number {
  const competenciasComuns = candidato.competencias.filter(c => 
    vaga.competenciasNecessarias.includes(c)
  );
  return (competenciasComuns.length / vaga.competenciasNecessarias.length) * 100;
}


document.addEventListener('DOMContentLoaded', () => {
  const listaCandidatosDiv = document.getElementById('listaCandidatos')!;
  const candidatos = carregarCandidatos();

  if (candidatos.length === 0) {
    listaCandidatosDiv.innerHTML = '<p>Não há candidatos disponíveis.</p>';
    return;
  }

  candidatos.forEach(candidato => {
    const candidatoDiv = document.createElement('div');
    candidatoDiv.classList.add('candidato-item');
    candidatoDiv.innerHTML = `
      <p>Nome: ${candidato.anonimo ? 'Anônimo' : candidato.nome}</p>
      <p>Competências: ${candidato.competencias.join(', ')}</p>
    `;
    listaCandidatosDiv.appendChild(candidatoDiv);
  });
});



function criarGraficoCompetencias(candidato: Candidato, vaga: Vaga) {
  const ctx = document.getElementById('competenciasChart') as HTMLCanvasElement;

  const competenciasComuns = candidato.competencias.filter(c => vaga.competenciasNecessarias.includes(c));
  const competenciasNaoComuns = candidato.competencias.filter(c => !vaga.competenciasNecessarias.includes(c));


  new Chart(ctx, {
    type: 'bar',
    data: {
      labels: ['Competências em Comum', 'Competências Não Alinhadas'],
      datasets: [{
        label: 'Competências',
        data: [competenciasComuns.length, competenciasNaoComuns.length],
        backgroundColor: ['rgba(75, 192, 192, 0.2)', 'rgba(255, 99, 132, 0.2)'],
        borderColor: ['rgba(75, 192, 192, 1)', 'rgba(255, 99, 132, 1)'],
        borderWidth: 1
      }]
    },
    options: {
      scales: {
        x: {
          beginAtZero: true
        },
        y: {
          beginAtZero: true
        }
      }
    }
  });
  
  
}


