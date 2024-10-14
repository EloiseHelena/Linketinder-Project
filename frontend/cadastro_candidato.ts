function validarCPF(cpf: string): boolean {
  const cpfLimpo = cpf.replace(/\D/g, '');
  return cpfLimpo.length === 11;
}

function cpfJaExiste(cpf: string): boolean {
  const candidatos = JSON.parse(localStorage.getItem('candidatos') || '[]');
  return candidatos.some((candidato: any) => candidato.cpf === cpf);
}

const formCandidato = document.getElementById('cadastroCandidatoForm') as HTMLFormElement;

formCandidato.addEventListener('submit', function (event) {
  event.preventDefault();

  const nome = (document.getElementById('nome') as HTMLInputElement).value;
  const cpf = (document.getElementById('cpf') as HTMLInputElement).value;
  const email = (document.getElementById('email') as HTMLInputElement).value;
  const senha = (document.getElementById('senha') as HTMLInputElement).value;
  const descricao = (document.getElementById('descricao') as HTMLInputElement).value;
  const competencias = (document.getElementById('competencias') as HTMLInputElement).value
  .split(',').map(c => c.trim());
  const dataNascimento = (document.getElementById('dataNascimento') as HTMLInputElement).value;
  const endereco = (document.getElementById('endereco') as HTMLInputElement).value;
  
  

  if (!validarCPF(cpf)) {
    alert('CPF inválido. Deve conter exatamente 11 dígitos.');
    return;
  }

  if (cpfJaExiste(cpf)) {
    alert('CPF já cadastrado!');
    return;
  }

  const candidato = { nome, cpf, email, senha, descricao, competencias, dataNascimento, endereco };
  const candidatos = JSON.parse(localStorage.getItem('candidatos') || '[]');
  candidatos.push(candidato);

  localStorage.setItem('candidatos', JSON.stringify(candidatos));
  alert('Candidato cadastrado com sucesso!');
  window.location.href = '/frontend/public/index.html';
});
