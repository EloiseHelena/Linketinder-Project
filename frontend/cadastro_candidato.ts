document.addEventListener('DOMContentLoaded', () => {
  const cadastroForm = document.getElementById('cadastroCandidatoForm') as HTMLFormElement;

  cadastroForm.addEventListener('submit', async (event) => {
    event.preventDefault();

    const nome = (document.getElementById('nome') as HTMLInputElement).value;
    const sobrenome = (document.getElementById('sobrenome') as HTMLInputElement).value;
    const dataNascimento = (document.getElementById('dataNascimento') as HTMLInputElement).value;
    const email = (document.getElementById('email') as HTMLInputElement).value;
    const cpf = (document.getElementById('cpf') as HTMLInputElement).value;
    const pais = (document.getElementById('pais') as HTMLInputElement).value;
    const cep = (document.getElementById('cep') as HTMLInputElement).value;
    const descricao = (document.getElementById('descricao') as HTMLInputElement).value;
    const senha = (document.getElementById('senha') as HTMLInputElement).value;

    try {
      const response = await fetch('/api/cadastro/candidato', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ nome, sobrenome, dataNascimento, email, cpf, pais, cep, descricao, senha }),
      });

      const result = await response.json();
      if (result.status === 'sucesso') {
        alert('Cadastro realizado com sucesso!');
        window.location.href = '/frontend/public/login.html';
      } else {
        alert(result.message);
      }
    } catch (error) {
      alert('Erro ao tentar realizar o cadastro.');
      console.error('Erro:', error);
    }
  });
});
