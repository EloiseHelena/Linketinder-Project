function validarCNPJ(cnpj: string): boolean {
  const regexCNPJ = /^\d{14}$/;
  return regexCNPJ.test(cnpj);
}

const formEmpresa = document.getElementById('cadastroEmpresaForm') as HTMLFormElement;

formEmpresa.addEventListener('submit', (event) => {
  event.preventDefault();

  const nome = (document.getElementById('nome') as HTMLInputElement).value;
  const cnpj = (document.getElementById('cnpj') as HTMLInputElement).value;
  const email = (document.getElementById('email') as HTMLInputElement).value;
  const endereco = (document.getElementById('endereco') as HTMLInputElement).value;
  const descricao = (document.getElementById('descricao') as HTMLTextAreaElement).value;
  const competencias = (document.getElementById('competencias') as HTMLInputElement).value.split(',');
  const senha = (document.getElementById('senha') as HTMLInputElement).value;

  if (!validarCNPJ(cnpj)) {
    alert('CNPJ inválido. Deve conter exatamente 14 dígitos.');
    return;
  }

  const empresa = { nome, cnpj, email, endereco, descricao, competencias, senha };
  const empresas = JSON.parse(localStorage.getItem('empresas') || '[]');
  empresas.push(empresa);

  localStorage.setItem('empresas', JSON.stringify(empresas));
  alert('Empresa cadastrada com sucesso!');
  window.location.href = '/frontend/public/index.html';
});
