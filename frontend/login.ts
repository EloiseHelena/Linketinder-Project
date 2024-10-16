document.addEventListener('DOMContentLoaded', () => {
  const loginForm = document.getElementById('loginForm') as HTMLFormElement;

  loginForm.addEventListener('submit', (event) => {
    event.preventDefault();

    const email = (document.getElementById('email') as HTMLInputElement).value;
    const senha = (document.getElementById('senha') as HTMLInputElement).value;

    const empresas = JSON.parse(localStorage.getItem('empresas') || '[]');
    const empresaLogada = empresas.find((empresa: any) => empresa.email === email && empresa.senha === senha);

    if (empresaLogada) {
      alert('Login realizado com sucesso!');
      localStorage.setItem('empresaLogada', JSON.stringify(empresaLogada));
      window.location.href = '/frontend/public/perfil_empresa.html';
    } else {
      alert('Email ou senha incorretos.');
    }
  });
});
