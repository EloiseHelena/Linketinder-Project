function validarCPF(cpf: string): boolean {
  const regexCPF = /^\d{11}$/;
  return regexCPF.test(cpf);
}

function validarEmail(email: string): boolean {
  const regexEmail = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  return regexEmail.test(email);
}

function validarSenha(senha: string): boolean {
  const regexSenha = /^.{8,}$/; 
  return regexSenha.test(senha);
}

const formCandidato = document.getElementById('cadastroCandidatoForm') as HTMLFormElement;

formCandidato.addEventListener('submit', (event) => {
  event.preventDefault();

  const nome = (document.getElementById('nome') as HTMLInputElement).value;
  const cpf = (document.getElementById('cpf') as HTMLInputElement).value;
  const email = (document.getElementById('email') as HTMLInputElement).value;
  const dataNascimento = (document.getElementById('dataNascimento') as HTMLInputElement).value;
  const endereco = (document.getElementById('endereco') as HTMLInputElement).value;
  const descricao = (document.getElementById('descricao') as HTMLTextAreaElement).value;
  const competencias = (document.getElementById('competencias') as HTMLInputElement).value.split(',');
  const senha = (document.getElementById('senha') as HTMLInputElement).value;

  if (!validarCPF(cpf)) {
    alert('CPF inválido. Deve conter exatamente 11 dígitos.');
    return;
  }

  if (!validarEmail(email)) {
    alert('Email inválido.');
    return;
  }

  if (!validarSenha(senha)) {
    alert('A senha deve ter no mínimo 8 caracteres.');
    return;
  }

  const candidato = { nome, cpf, email, dataNascimento, endereco, descricao, competencias, senha };
  const candidatos = JSON.parse(localStorage.getItem('candidatos') || '[]');
  candidatos.push(candidato);

  localStorage.setItem('candidatos', JSON.stringify(candidatos));
  alert('Candidato cadastrado com sucesso!');
  window.location.href = '/frontend/public/index.html';
});
