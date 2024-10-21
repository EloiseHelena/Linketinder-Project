package src
import candidatos.Candidato
import empresa.Empresa
import db.DatabaseConnection


def menu(candidatos, empresas) {
    while (true) {
        Scanner scanner = new Scanner(System.in)
        println "Bem-vindo ao Linketinder!"
        println "Escolha uma opção:"
        println "1. Listar todos os candidatos"
        println "2. Listar todas as empresas"
        println "3. Cadastrar novo candidato"
        println "4. Cadastrar nova empresa"
        println "5. Exibir matches"
        println "6. Sair"

        def option = scanner.nextInt()

        switch (option) {
            case 1:
                listarCandidatos(candidatos)
                break
            case 2:
                listarEmpresas(empresas)
                break
            case 3:
                cadastrarCandidato(candidatos)
                salvarCandidatoNoBanco(candidatos.last())
                break

            case 4:
                cadastrarEmpresa(empresas)
                salvarEmpresaNoBanco(empresas as Empresa)
                break
            case 5:
                exibirMatches(candidatos, empresas)
                break
            case 6:
                println "Saindo..."
                return
            default:
                println "Opção inválida, tente novamente."
        }
    }
}


def listarCandidatos(candidatos) {
    println "Candidatos:"
    candidatos.each { candidato ->
        println "Nome: ${candidato.nome}, Competências: ${candidato.competencias.join(', ')}"
    }
    menu(candidatos)
}

def listarEmpresas(empresas) {
    println "Empresas:"
    empresas.each { empresa ->
        println "Nome: ${empresa.nome}, Competências desejadas: ${empresa.competencias.join(', ')}"
    }
    menu(empresas)
}

def cadastrarCandidato(candidatos) {
    Scanner scanner = new Scanner(System.in)

    println "Cadastro de Novo Candidato"

    println "Digite o nome:"
    String nome = scanner.nextLine()

    println "Digite o sobrenome:"
    String sobrenome = scanner.nextLine()

    println "Digite o email:"
    String email = scanner.nextLine()

    println "Digite sua descrição:"
    String descricao = scanner.nextLine()

    println "Digite o CPF:"
    String cpf = scanner.nextLine()

    println "Digite a data de nascimento (formato: yyyy-MM-dd):"
    String dataNascimento = scanner.nextLine()

    println "Digite o país:"
    String pais = scanner.nextLine()

    println "Digite o CEP:"
    String cep = scanner.nextLine()


    def novoCandidato = new Candidato(nome, sobrenome, dataNascimento,email, cpf, pais, cep, descricao)
    candidatos.add(novoCandidato)

    println "Candidato cadastrado com sucesso!"

    menu(candidatos)
}


def cadastrarEmpresa(empresas) {
    Scanner scanner = new Scanner(System.in)

    println "Cadastro de Nova Empresa"

    println "Digite o nome:"
    String nome = scanner.nextLine()

    println "Digite o email corporativo:"
    String email = scanner.nextLine()

    println "Digite a descrição:"
    String descricao = scanner.nextLine()

    println "Digite as competências desejadas (separadas por vírgula):"
    String competenciasStr = scanner.nextLine()
    List<String> competencias = competenciasStr.split(',').collect { it.trim() }

    println "Digite o CNPJ:"
    String cnpj = scanner.nextLine()

    println "Digite o país:"
    String pais = scanner.nextLine()

    println "Digite o estado:"
    String estado = scanner.nextLine()

    println "Digite o CEP:"
    String cep = scanner.nextLine()

    def novaEmpresa = new Empresa(nome, email, descricao, competencias, cnpj, pais, estado, cep)
    empresas.add(novaEmpresa)

    println "Empresa cadastrada com sucesso!"

    menu(empresas)
}

def exibirMatches(candidatos, empresas) {
    Scanner scanner = new Scanner(System.in)

    println "Digite o nome do candidato que deseja curtir uma empresa:"
    String nomeCandidato = scanner.nextLine()
    Candidato candidato = candidatos.find { it.nome == nomeCandidato } as Candidato

    if (candidato) {
        println "Digite o nome da empresa que deseja curtir:"
        String nomeEmpresa = scanner.nextLine()
        Empresa empresa = empresas.find { it.nome == nomeEmpresa } as Empresa

        if (empresa) {
            println "Candidato ${candidato.nome} curtiu a empresa ${empresa.nome}"
            println "Empresa ${empresa.nome} deseja curtir ${candidato.nome}? (s/n)"
            String resposta = scanner.nextLine()

            if (resposta.toLowerCase() == 's') {
                println "Match! ${candidato.nome} e ${empresa.nome} podem agora se comunicar."
            } else {
                println "A empresa não curtiu o candidato."
            }
        } else {
            println "Empresa não encontrada."
        }
    } else {
        println "Candidato não encontrado."
    }

    menu(candidatos, empresas)
}

def carregarCandidatosDoBanco() {
    def conn = DatabaseConnection.getConnection()
    def candidatos = []
    def sql = "SELECT * FROM candidatos"
    def stmt = conn.prepareStatement(sql)
    def rs = stmt.executeQuery()

    while (rs.next()) {
        def candidato = new Candidato(
                rs.getString("nome"),
                rs.getString("sobrenome"),
                rs.getString("dataDeNascimento"),
                rs.getString("email"),
                rs.getString("cpf"),
                rs.getString("pais"),
                rs.getString("cep"),
                rs.getString("descricao"),
        )
        candidatos.add(candidato)
    }
    return candidatos
}


def carregarEmpresasDoBanco() {
    def conn = DatabaseConnection.getConnection()
    def empresas = []
    def sql = "SELECT * FROM empresas"
    def stmt = conn.prepareStatement(sql)
    def rs = stmt.executeQuery()

    while (rs.next()) {
        def empresa = new Empresa(
                rs.getString("nome"),
                rs.getString("email"),
                rs.getString("descricao"),
                rs.getArray("competencias").array as List<String>,
                rs.getString("cnpj"),
                rs.getString("pais"),
                rs.getString("estado"),
                rs.getString("cep")
        )
        empresas.add(empresa)
    }
    return empresas
}


def salvarCandidatoNoBanco(Candidato candidato) {
    def conn = DatabaseConnection.getConnection()
    def sql = "INSERT INTO candidatos (nome, sobrenome, data_nascimento, email, cpf, pais, cep, descricao) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
    def stmt = conn.prepareStatement(sql)

    stmt.setString(1, candidato.nome)
    stmt.setString(2, candidato.sobrenome)
    stmt.setDate(3, java.sql.Date.valueOf(candidato.dataNascimento))
    stmt.setString(4, candidato.email)
    String cpfLimpo = candidato.cpf.replaceAll("[^0-9]", "")
    stmt.setString(5, cpfLimpo)
    stmt.setString(6, candidato.pais)
    stmt.setString(7, candidato.cep)
    stmt.setString(8, candidato.descricao)

    stmt.executeUpdate()
    println "Candidato salvo no banco de dados."
}


def salvarEmpresaNoBanco(Empresa empresa) {
    def conn = DatabaseConnection.getConnection()
    def sql = "INSERT INTO empresas (nome, email, descricao, competencias, cnpj, pais, estado, cep) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
    def stmt = conn.prepareStatement(sql)

    stmt.setString(1, empresa.nome)
    stmt.setString(2, empresa.email)
    stmt.setString(3, empresa.descricao)
    stmt.setArray(4, conn.createArrayOf("VARCHAR", empresa.competencias.toArray())) // assumindo que você tem uma coluna de array no PostgreSQL
    stmt.setString(5, empresa.cnpj)
    stmt.setString(6, empresa.pais)
    stmt.setString(7, empresa.estado)
    stmt.setString(8, empresa.cep)

    stmt.executeUpdate()
    println "Empresa salva no banco de dados."
}



def candidatos = [
        new Candidato("João", "Silva", "1990-05-12",  "Desenvolvedor", "joao@example.com", "12345678900", "Brasil", "01000-000"),
        new Candidato("Maria", "Oliveira", "1992-08-15", "Frontend", "maria@example.com", "98765432100", "Brasil", "20000-000"),
        new Candidato("Carlos", "Souza", "1985-02-20",  "AWS", "carlos@example.com", "12312312312", "Brasil", "30000-000"),
        new Candidato("Ana", "Costa", "1995-10-05", "JavaScript", "ana@example.com", "45645645645", "Brasil", "40000-000"),
        new Candidato("Rafael", "Lima", "1988-11-22", "SQL", "rafael@example.com", "78978978978", "Brasil", "50000-000")
]



def empresas = [
        new Empresa("Tech Solutions", "contato@techsolutions.com", "Empresa de tecnologia focada em soluções web", ["Java", "Spring"], "12345678000100", "Brasil", "SP", "01000-000"),
        new Empresa("DataCorp", "hr@datacorp.com", "Empresa especializada em Big Data e análise de dados", ["Python", "SQL"], "98765432000100", "Brasil", "RJ", "20000-000"),
        new Empresa("WebMasters", "jobs@webmasters.com", "Agência de desenvolvimento web", ["HTML", "CSS", "JavaScript"], "12312312000100", "Brasil", "MG", "30000-000"),
        new Empresa("Cloud Innovators", "recruitment@cloudinnovators.com", "Empresa focada em soluções de nuvem", ["AWS", "DevOps"], "45645645000100", "Brasil", "SP", "40000-000"),
        new Empresa("MobileWorks", "careers@mobileworks.com", "Desenvolvedora de aplicativos móveis", ["JavaScript", "Angular"], "78978978000100", "Brasil", "BA", "50000-000")
]

menu(candidatos, empresas)


static Object menu(Object o) {
    null
}