"use strict";
var _a;
// Função para iniciar o chat após o match
function iniciarChat(matchNome) {
    const chatArea = document.getElementById("chatArea");
    const matchNomeDisplay = document.getElementById("matchNome");
    const messageHistory = document.getElementById("messageHistory");
    if (chatArea && matchNomeDisplay && messageHistory) {
        chatArea.style.display = 'block'; // Exibir área de chat
        matchNomeDisplay.innerText = matchNome; // Exibir nome do match
        carregarMensagens(matchNome);
    }
}
// Função para carregar mensagens de um match específico
function carregarMensagens(matchNome) {
    const messageHistory = document.getElementById("messageHistory");
    const mensagens = JSON.parse(localStorage.getItem(`mensagens_${matchNome}`) || '[]');
    if (messageHistory) {
        messageHistory.innerHTML = ''; // Limpar mensagens anteriores
        mensagens.forEach((msg) => {
            const messageDiv = document.createElement('div');
            messageDiv.innerText = msg;
            messageHistory.appendChild(messageDiv);
        });
    }
}
// Função para enviar mensagem
function enviarMensagem(matchNome) {
    const messageInput = document.getElementById("messageInput");
    const messageHistory = document.getElementById("messageHistory");
    if (messageInput && messageHistory) {
        const novaMensagem = messageInput.value;
        if (novaMensagem.trim() !== '') {
            const mensagens = JSON.parse(localStorage.getItem(`mensagens_${matchNome}`) || '[]');
            mensagens.push(novaMensagem); // Adicionar nova mensagem
            localStorage.setItem(`mensagens_${matchNome}`, JSON.stringify(mensagens)); // Salvar no localStorage
            // Exibir a nova mensagem no histórico
            const messageDiv = document.createElement('div');
            messageDiv.innerText = novaMensagem;
            messageHistory.appendChild(messageDiv);
            // Limpar o campo de texto
            messageInput.value = '';
        }
    }
}
// Evento de clique para o botão de envio
(_a = document.getElementById("sendMessage")) === null || _a === void 0 ? void 0 : _a.addEventListener('click', () => {
    var _a;
    const matchNome = (_a = document.getElementById("matchNome")) === null || _a === void 0 ? void 0 : _a.innerText;
    if (matchNome) {
        enviarMensagem(matchNome);
    }
});
