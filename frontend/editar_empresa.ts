
document.addEventListener('DOMContentLoaded', () => {
    const empresaLogada = JSON.parse(localStorage.getItem('empresaLogada') || 'null');

    if (empresaLogada) {
        (document.getElementById('nome') as HTMLInputElement).value = empresaLogada.nome;
        (document.getElementById('email') as HTMLInputElement).value = empresaLogada.email;
        (document.getElementById('descricao') as HTMLTextAreaElement).value = empresaLogada.descricao;
        (document.getElementById('competencias') as HTMLInputElement).value = empresaLogada.competencias.join(', ');
        (document.getElementById('endereco') as HTMLInputElement).value = empresaLogada.endereco;
    }
});

const formEditarEmpresa = document.getElementById('formEditarEmpresa') as HTMLFormElement;
formEditarEmpresa.addEventListener('submit', function (event) {
    event.preventDefault();

    const nome = (document.getElementById('nome') as HTMLInputElement).value;
    const email = (document.getElementById('email') as HTMLInputElement).value;
    const descricao = (document.getElementById('descricao') as HTMLTextAreaElement).value;
    const competencias = (document.getElementById('competencias') as HTMLInputElement).value.split(',').map(c => c.trim());
    const endereco = (document.getElementById('endereco') as HTMLInputElement).value;

    const empresaAtualizada = {
        ...JSON.parse(localStorage.getItem('empresaLogada') || '{}'),
        nome,
        email,
        descricao,
        competencias,
        endereco
    };

    localStorage.setItem('empresaLogada', JSON.stringify(empresaAtualizada));

    alert('Perfil da empresa atualizado com sucesso!');
    window.location.href = '/frontend/public/perfil_empresa.html';
});
