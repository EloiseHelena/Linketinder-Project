function validarCNPJ(cnpj: string): boolean {
  const cnpjLimpo = cnpj.replace(/\D/g, '');
  return cnpjLimpo.length === 14;
}

function cnpjJaExiste(cnpj: string): boolean {
  const empresas = JSON.parse(localStorage.getItem('empresas') || '[]');
  return empresas.some((empresa: any) => empresa.cnpj === cnpj);
}

const formEmpresa = document.getElementById('cadastroEmpresaForm') as HTMLFormElement;

formEmpresa.addEventListener('submit', function (event) {
  event.preventDefault();

  const nome = (document.getElementById('nome') as HTMLInputElement).value;
  const cnpj = (document.getElementById('cnpj') as HTMLInputElement).value;
  const email = (document.getElementById('email') as HTMLInputElement).value;
  const senha = (document.getElementById('senha') as HTMLInputElement).value;
  const descricao = (document.getElementById('descricao') as HTMLInputElement).value;
  const competencias = (document.getElementById('competencias') as HTMLInputElement).value
  .split(',').map(c => c.trim());
  const endereco = (document.getElementById('endereco') as HTMLInputElement).value;

  if (!validarCNPJ(cnpj)) {
    alert('CNPJ inválido. Deve conter exatamente 14 dígitos.');
    return;
  }

  if (cnpjJaExiste(cnpj)) {
    alert('CNPJ já cadastrado!');
    return;
  }

  const empresa = { nome, cnpj, email, senha, descricao, competencias, endereco };
  const empresas = JSON.parse(localStorage.getItem('empresas') || '[]');
  empresas.push(empresa);

  localStorage.setItem('empresas', JSON.stringify(empresas));
  alert('Empresa cadastrada com sucesso!');
  window.location.href = '/frontend/public/index.html';
});
