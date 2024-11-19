package src

import models.Candidato
import models.Empresa
import services.CandidatoService
import services.EmpresaService
import services.CompetenciaService

class Main {

    private static final CandidatoService candidatoService = new CandidatoService()
    private static final EmpresaService empresaService = new EmpresaService()
    private static final CompetenciaService competenciaService = new CompetenciaService()

    static void menu() {
        while (true) {
            println "Bem-vindo ao Linketinder!"
            println "Escolha uma opção:"
            println "1. Listar todos os candidatos"
            println "2. Listar todas as empresas"
            println "3. Cadastrar novo candidato"
            println "4. Cadastrar nova empresa"
            println "5. Exibir matches"
            println "6. Sair"

            Scanner scanner = new Scanner(System.in)
            int option = scanner.nextInt()

            switch (option) {
                case 1:
                    listarEntidades(candidatoService.listarTodos(), "Candidatos")
                    break
                case 2:
                    listarEntidades(empresaService.listarTodos(), "Empresas")
                    break
                case 3:
                    Candidato novoCandidato = cadastrarCandidato()
                    candidatoService.cadastrarCandidato(novoCandidato)
                    break
                case 4:
                    Empresa novaEmpresa = cadastrarEmpresa()
                    empresaService.cadastrarEmpresa(novaEmpresa)
                    break
                case 5:
                    exibirMatches()
                    break
                case 6:
                    println "Saindo..."
                    return
                default:
                    println "Opção inválida, tente novamente."
            }
        }
    }

    static void listarEntidades(List entidades, String tipo) {
        println "$tipo:"
        entidades.each { entidade ->
            println entidade.toString()
        }
    }

    static Candidato cadastrarCandidato() {
        println "Cadastro de Novo Candidato"
        Scanner scanner = new Scanner(System.in)

        String nome = lerInput(scanner, "Digite o nome:")
        String sobrenome = lerInput(scanner, "Digite o sobrenome:")
        String email = lerInput(scanner, "Digite o email:")
        String descricao = lerInput(scanner, "Digite sua descrição:")
        String cpf = lerInput(scanner, "Digite o CPF:")
        String dataNascimento = lerInput(scanner, "Digite a data de nascimento (yyyy-MM-dd):")
        String pais = lerInput(scanner, "Digite o país:")
        String cep = lerInput(scanner, "Digite o CEP:")
        List<String> competencias = listarCompetencias(scanner)

        return new Candidato(nome, sobrenome, dataNascimento, email, cpf, pais, cep, descricao, competencias)
    }

    static Empresa cadastrarEmpresa() {
        println "Cadastro de Nova Empresa"
        Scanner scanner = new Scanner(System.in)

        String nome = lerInput(scanner, "Digite o nome da empresa:")
        String email = lerInput(scanner, "Digite o email corporativo:")
        String descricao = lerInput(scanner, "Digite a descrição:")
        String cnpj = lerInput(scanner, "Digite o CNPJ:")
        String pais = lerInput(scanner, "Digite o país:")
        String estado = lerInput(scanner, "Digite o estado:")
        String cep = lerInput(scanner, "Digite o CEP:")

        List<String> competencias = listarCompetencias(scanner)

        return new Empresa(nome, email, descricao, competencias, cnpj, pais, estado, cep)
    }

    static List<String> listarCompetencias(Scanner scanner) {
        println "Digite as competências (separe por vírgulas):"
        String competenciasInput = lerInput(scanner, "")
        return competenciasInput.split(',').collect { it.trim() }
    }

    static String lerInput(Scanner scanner, String mensagem) {
        println mensagem
        return scanner.nextLine()
    }

    static void exibirMatches() {
        println "Funcionalidade de exibir matches ainda não implementada."
    }

    static void main(String[] args) {
        menu()
    }
}





