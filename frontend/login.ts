document.addEventListener('DOMContentLoaded', () => {
  const loginForm = document.getElementById('loginForm') as HTMLFormElement;

  loginForm.addEventListener('submit', (event) => {
    event.preventDefault();

    const email = (document.getElementById('email') as HTMLInputElement).value;
    const senha = (document.getElementById('senha') as HTMLInputElement).value;

    
    const empresas = JSON.parse(localStorage.getItem('empresas') || '[]');
    const empresaLogada = empresas.find((empresa: any) => empresa.email === email && empresa.senha === senha);

    const candidatos = JSON.parse(localStorage.getItem('candidatos') || '[]');
    const candidatoLogado = candidatos.find((candidato: any) => candidato.email === email && candidato.senha === senha);

    if (empresaLogada) {
      alert('Login realizado com sucesso! Empresa logada.');
      localStorage.setItem('empresaLogada', JSON.stringify(empresaLogada));
      window.location.href = '/frontend/public/perfil_empresa.html';
    } else if (candidatoLogado) {
      alert('Login realizado com sucesso! Candidato logado.');
      localStorage.setItem('candidatoLogado', JSON.stringify(candidatoLogado));
      window.location.href = '/frontend/public/perfil_candidato.html';
    } else {
      alert('Email ou senha incorretos.');
    }
  });
});
