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
  .catch((err: Error) => console.error('Erro ao conectar ao PostgreSQL:', err));


export const adicionarCandidato = async (
  nome: string,
  sobrenome: string,
  dataNascimento: string,
  email: string,
  cpf: string,
  pais: string,
  cep: string,
  descricao: string,
  senha: string
) => {
  try {
    const res = await client.query(
      'INSERT INTO candidatos (nome, sobrenome, data_nascimento, email, cpf, pais, cep, descricao, senha) VALUES ($1, $2, $3, $4, $5, $6, $7, $8, $9) RETURNING *',
      [nome, sobrenome, dataNascimento, email, cpf, pais, cep, descricao, senha]
    );
    return res.rows[0];
  } catch (err) {
    console.error('Erro ao inserir candidato:', err);
  }
};

export const buscarCandidatos = async () => {
  try {
    const res = await client.query('SELECT * FROM candidatos');
    return res.rows;
  } catch (err) {
    console.error('Erro ao buscar candidatos:', err);
  }
};

