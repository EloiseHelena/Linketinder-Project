document.addEventListener('DOMContentLoaded', () => {
  const loginForm = document.getElementById('loginForm') as HTMLFormElement;

  loginForm.addEventListener('submit', async (event) => {
    event.preventDefault();

    const email = (document.getElementById('email') as HTMLInputElement).value;
    const senha = (document.getElementById('senha') as HTMLInputElement).value;

    try {
      const response = await fetch('/api/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ email, senha }),
      });

      const result = await response.json();

      if (result.status === 'sucesso') {
        if (result.tipo === 'empresa') {
          alert('Login realizado com sucesso! Empresa logada.');
          localStorage.setItem('empresaLogada', JSON.stringify(result.dados));
          window.location.href = '/frontend/public/perfil_empresa.html';
        } else if (result.tipo === 'candidato') {
          alert('Login realizado com sucesso! Candidato logado.');
          localStorage.setItem('candidatoLogado', JSON.stringify(result.dados));
          window.location.href = '/frontend/public/perfil_candidato.html';
        }
      } else {
        alert(result.message);
      }
    } catch (error) {
      alert('Erro ao tentar realizar o login.');
      console.error('Erro:', error);
    }
  });
});
