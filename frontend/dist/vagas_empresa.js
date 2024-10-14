document.addEventListener('DOMContentLoaded', () => {
    const formVaga = document.getElementById('formVaga');
    formVaga.addEventListener('submit', function (event) {
        event.preventDefault();
        const titulo = document.getElementById('titulo').value;
        const descricao = document.getElementById('descricao').value;
        const competencias = document.getElementById('competencias')
            .value.split(',')
            .map((c) => c.trim());
        const anonimo = document.getElementById('anonimo').checked;
        const novaVaga = { titulo, descricao, competenciasNecessarias: competencias, anonimo };
        const vagasSalvas = JSON.parse(localStorage.getItem('vagas') || '[]');
        vagasSalvas.push(novaVaga);
        localStorage.setItem('vagas', JSON.stringify(vagasSalvas));
        alert('Vaga cadastrada com sucesso!');
        formVaga.reset();
    });
});
export {};
