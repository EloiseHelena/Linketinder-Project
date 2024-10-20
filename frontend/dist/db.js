var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
import { Client } from 'pg';
const client = new Client({
    user: 'linketinder_user',
    host: 'localhost',
    database: 'linketinder_db',
    password: 'acelera',
    port: 5432,
});
client.connect()
    .then(() => console.log('Conectado ao PostgreSQL com sucesso!'))
    .catch((err) => console.error('Erro ao conectar ao PostgreSQL:', err));
export const adicionarCandidato = (nome, sobrenome, dataNascimento, email, cpf, pais, cep, descricao, senha) => __awaiter(void 0, void 0, void 0, function* () {
    try {
        const res = yield client.query('INSERT INTO candidatos (nome, sobrenome, data_nascimento, email, cpf, pais, cep, descricao, senha) VALUES ($1, $2, $3, $4, $5, $6, $7, $8, $9) RETURNING *', [nome, sobrenome, dataNascimento, email, cpf, pais, cep, descricao, senha]);
        return res.rows[0];
    }
    catch (err) {
        console.error('Erro ao inserir candidato:', err);
    }
});
export const buscarCandidatos = () => __awaiter(void 0, void 0, void 0, function* () {
    try {
        const res = yield client.query('SELECT * FROM candidatos');
        return res.rows;
    }
    catch (err) {
        console.error('Erro ao buscar candidatos:', err);
    }
});
