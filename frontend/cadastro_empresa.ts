document.addEventListener('DOMContentLoaded', () => {
  const cadastroForm = document.getElementById('cadastroEmpresaForm') as HTMLFormElement;

  cadastroForm.addEventListener('submit', async (event) => {
    event.preventDefault();

    const nomeEmpresa = (document.getElementById('nomeEmpresa') as HTMLInputElement).value;
    const cnpj = (document.getElementById('cnpj') as HTMLInputElement).value;
    const email = (document.getElementById('email') as HTMLInputElement).value;
    const descricao = (document.getElementById('descricao') as HTMLInputElement).value;
    const pais = (document.getElementById('pais') as HTMLInputElement).value;
    const cep = (document.getElementById('cep') as HTMLInputElement).value;
    const senha = (document.getElementById('senha') as HTMLInputElement).value;

    try {
      const response = await fetch('/api/cadastro/empresa', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ nomeEmpresa, cnpj, email, descricao, pais, cep, senha }),
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
