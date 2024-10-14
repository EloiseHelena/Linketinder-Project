import { Vaga } from './vagas.js'; 

document.addEventListener('DOMContentLoaded', () => {
  const formVaga = document.getElementById('formVaga') as HTMLFormElement;

  formVaga.addEventListener('submit', function (event) {
    event.preventDefault();

    const titulo = (document.getElementById('titulo') as HTMLInputElement).value;
    const descricao = (document.getElementById('descricao') as HTMLInputElement).value;
    const competencias = (document.getElementById('competencias') as HTMLInputElement)
      .value.split(',')
      .map((c) => c.trim());
    const anonimo = (document.getElementById('anonimo') as HTMLInputElement).checked;

    const novaVaga: Vaga = { titulo, descricao, competenciasNecessarias: competencias, anonimo };
    
    const vagasSalvas = JSON.parse(localStorage.getItem('vagas') || '[]');
    vagasSalvas.push(novaVaga);
    localStorage.setItem('vagas', JSON.stringify(vagasSalvas));

    alert('Vaga cadastrada com sucesso!');
    formVaga.reset();
  });
});
