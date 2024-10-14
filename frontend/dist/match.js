import Swal from 'sweetalert2';
let curtidasCandidato = JSON.parse(localStorage.getItem('curtidasCandidato') || '[]');
let curtidasEmpresa = JSON.parse(localStorage.getItem('curtidasEmpresa') || '[]');
function curtirVaga(vagaTitulo) {
    let curtidasCandidato = JSON.parse(localStorage.getItem('curtidasCandidato') || '[]');
    const heartIcon = document.getElementById(`curtir-${vagaTitulo}`);
    if (!curtidasCandidato.includes(vagaTitulo)) {
        curtidasCandidato.push(vagaTitulo);
        localStorage.setItem('curtidasCandidato', JSON.stringify(curtidasCandidato));
        if (heartIcon) {
            heartIcon.classList.add('curtido');
        }
        notificarCurtida(vagaTitulo);
        verificarMatch(vagaTitulo);
    }
    else {
        Swal.fire('Aviso!', 'Você já curtiu esta vaga.', 'info');
    }
}
function curtirCandidato(candidatoNome) {
    let curtidasEmpresa = JSON.parse(localStorage.getItem('curtidasEmpresa') || '[]');
    if (!curtidasEmpresa.includes(candidatoNome)) {
        curtidasEmpresa.push(candidatoNome);
        localStorage.setItem('curtidasEmpresa', JSON.stringify(curtidasEmpresa));
        notificarCurtida(candidatoNome);
        verificarMatch(candidatoNome);
    }
    else {
        Swal.fire('Aviso!', 'Você já curtiu este candidato.', 'info');
    }
}
function verificarMatch(item) {
    let curtidasCandidato = JSON.parse(localStorage.getItem('curtidasCandidato') || '[]');
    let curtidasEmpresa = JSON.parse(localStorage.getItem('curtidasEmpresa') || '[]');
    if (curtidasCandidato.includes(item) && curtidasEmpresa.includes(item)) {
        alert(`Match encontrado! Agora você pode conversar com ${item}.`);
        const vagaDiv = document.getElementById(`vaga-${item}`);
        if (vagaDiv) {
            vagaDiv.classList.add('match');
        }
        iniciarChat(item);
    }
}
function iniciarChat(matchNome) {
    const chatArea = document.getElementById("chatArea");
    const matchNomeDisplay = document.getElementById("matchNome");
    const messageHistory = document.getElementById("messageHistory");
    if (chatArea && matchNomeDisplay && messageHistory) {
        chatArea.style.display = 'block';
        matchNomeDisplay.innerText = matchNome;
        carregarMensagens(matchNome);
    }
}
function notificarCurtida(item) {
    Swal.fire({
        title: 'Sucesso!',
        text: `Você curtiu ${item}`,
        icon: 'success',
        confirmButtonText: 'OK'
    });
}
function notificarMatch(item) {
    Swal.fire({
        title: 'Match Encontrado!',
        text: `Você agora pode ver as informações completas de ${item}.`,
        icon: 'success',
        confirmButtonText: 'OK'
    });
}
function atualizarVagaRevelada(vaga) {
    const vagaDiv = document.getElementById(`vaga-${vaga.titulo}`);
    if (vagaDiv) {
        const empresaSpan = vagaDiv.querySelector('.empresa');
        if (empresaSpan) {
            empresaSpan.innerText = vaga.nomeEmpresa || 'Anônimo';
        }
    }
}
function atualizarCandidatoRevelado(candidato) {
    const candidatoDiv = document.getElementById(`candidato-${candidato.nome}`);
    if (candidatoDiv) {
        const nomeSpan = candidatoDiv.querySelector('.nome');
        if (nomeSpan) {
            nomeSpan.innerText = candidato.nome;
        }
    }
}
function exibirCurtidasCandidato() {
    const listaCurtidasCandidato = document.getElementById('listaCurtidasCandidato');
    if (listaCurtidasCandidato) {
        listaCurtidasCandidato.innerHTML = '';
        if (curtidasCandidato.length === 0) {
            listaCurtidasCandidato.innerHTML = '<li>Nenhuma vaga curtida ainda.</li>';
        }
        else {
            curtidasCandidato.forEach((vaga) => {
                const li = document.createElement('li');
                li.textContent = vaga;
                listaCurtidasCandidato.appendChild(li);
            });
        }
    }
}
function exibirCurtidasEmpresa() {
    const listaCurtidasEmpresa = document.getElementById('listaCurtidasEmpresa');
    if (listaCurtidasEmpresa) {
        listaCurtidasEmpresa.innerHTML = '';
        if (curtidasEmpresa.length === 0) {
            listaCurtidasEmpresa.innerHTML = '<li>Nenhum candidato curtido ainda.</li>';
        }
        else {
            curtidasEmpresa.forEach((candidato) => {
                const li = document.createElement('li');
                li.textContent = candidato;
                listaCurtidasEmpresa.appendChild(li);
            });
        }
    }
}
function carregarDadosRevelados() {
    const vagasReveladas = JSON.parse(localStorage.getItem('vagasReveladas') || '[]');
    const candidatosRevelados = JSON.parse(localStorage.getItem('candidatosRevelados') || '[]');
    vagasReveladas.forEach((vaga) => {
        atualizarVagaRevelada(vaga);
    });
    candidatosRevelados.forEach((candidato) => {
        atualizarCandidatoRevelado(candidato);
    });
}
function calcularAfinidade(candidato, vaga) {
    const competenciasComuns = candidato.competencias.filter(c => vaga.competenciasNecessarias.includes(c));
    const afinidade = (competenciasComuns.length / vaga.competenciasNecessarias.length) * 100;
    return afinidade;
}
function recomendarVagas(candidato, vagas) {
    return vagas
        .map(vaga => ({
        vaga,
        afinidade: calcularAfinidade(candidato, vaga)
    }))
        .filter(({ afinidade }) => afinidade > 50)
        .sort((a, b) => b.afinidade - a.afinidade)
        .map(({ vaga }) => vaga);
}
function exibirVagasRecomendadas(candidato, vagas) {
    const vagasRecomendadas = recomendarVagas(candidato, vagas);
    const listaVagasDiv = document.getElementById('listaVagasRecomendadas');
    if (listaVagasDiv) {
        listaVagasDiv.innerHTML = '';
        if (vagasRecomendadas.length === 0) {
            listaVagasDiv.innerHTML = '<p>Nenhuma vaga recomendada.</p>';
        }
        else {
            vagasRecomendadas.forEach(vaga => {
                const vagaDiv = document.createElement('div');
                vagaDiv.innerHTML = `
            <h3>${vaga.titulo}</h3>
            <p>${vaga.descricao}</p>
            <p>Competências Necessárias: ${vaga.competenciasNecessarias.join(', ')}</p>
          `;
                listaVagasDiv.appendChild(vagaDiv);
            });
        }
    }
}
function carregarCurtidas() {
    exibirCurtidasCandidato();
    exibirCurtidasEmpresa();
}
carregarDadosRevelados();
carregarCurtidas();
