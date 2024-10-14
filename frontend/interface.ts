interface Candidato {
    nome: string;
    email: string;
    descricao: string;
    competencias: string[];
    cpf: string;
    dataNascimento: Date;
    endereco: string;
  }
  
  interface Empresa {
    nome: string;
    email: string;
    descricao: string;
    competencias: string[];
    cnpj: string;
    endereco: string;
  }

  
  interface Vaga {
    titulo: string;
    descricao: string;
    competenciasNecessarias: string[];
  }
  
  interface Match {
    candidato: Candidato;
    empresa: Empresa;
    afinidade: number;
  }
  