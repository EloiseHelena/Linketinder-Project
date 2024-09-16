package src

import src.candidatos.Candidato
import src.empresa.Empresa

def menu(candidatos, empresas) {
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
            break
        case 4:
            cadastrarEmpresa(empresas)
            break
        case 5:
            exibirMatches(candidatos, empresas)
            break
        case 6:
            println "Saindo..."
            System.exit(0)
        default:
            println "Opção inválida, tente novamente."
            menu(candidatos, empresas)
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

    println "Digite o email:"
    String email = scanner.nextLine()

    println "Digite a descrição:"
    String descricao = scanner.nextLine()

    println "Digite as competências (separadas por vírgula):"
    String competenciasStr = scanner.nextLine()
    List<String> competencias = competenciasStr.split(',').collect { it.trim() }

    println "Digite o CPF:"
    String cpf = scanner.nextLine()

    println "Digite a idade:"
    int idade = scanner.nextInt()
    scanner.nextLine()

    println "Digite o estado:"
    String estado = scanner.nextLine()

    println "Digite o CEP:"
    String cep = scanner.nextLine()

    def novoCandidato = new Candidato(nome, email, descricao, competencias, cpf, idade, estado, cep)
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
    Candidato candidato = candidatos.find { it.nome == nomeCandidato }

    if (candidato) {
        println "Digite o nome da empresa que deseja curtir:"
        String nomeEmpresa = scanner.nextLine()
        Empresa empresa = empresas.find { it.nome == nomeEmpresa }

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


def candidatos = [
        new Candidato("João Silva", "joao@example.com", "Desenvolvedor Java", ["Java", "Spring"], "12345678900", 30, "SP", "01000-000"),
        new Candidato("Maria Oliveira", "maria@example.com", "Desenvolvedora Fullstack", ["JavaScript", "Angular"], "98765432100", 28, "RJ", "20000-000"),
        new Candidato("Carlos Souza", "carlos@example.com", "DevOps", ["Python", "AWS"], "12312312312", 35, "MG", "30000-000"),
        new Candidato("Ana Costa", "ana@example.com", "Desenvolvedora Frontend", ["HTML", "CSS", "JavaScript"], "45645645645", 25, "SP", "40000-000"),
        new Candidato("Rafael Lima", "rafael@example.com", "Engenheiro de Dados", ["Python", "SQL"], "78978978978", 32, "BA", "50000-000")
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