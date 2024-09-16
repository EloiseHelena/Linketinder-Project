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