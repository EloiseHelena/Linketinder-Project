package src
import candidatos.Candidato
import empresa.Empresa
import dao.CandidatoDAO
import dao.EmpresaDAO

def candidatoDAO = new CandidatoDAO()
def empresaDAO = new EmpresaDAO()



def menu() {
    while (true) {
        println "Bem-vindo ao Linketinder!"
        println "Escolha uma opção:"
        println "1. Listar todos os candidatos"
        println "2. Listar todas as empresas"
        println "3. Cadastrar novo candidato"
        println "4. Cadastrar nova empresa"
        println "5. Exibir matches"
        println "6. Sair"

        def option = new Scanner(System.in).nextInt()
        switch (option) {
            case 1:
                listarEntidades(candidatoDAO.listarTodos(), "Candidatos")
                break
            case 2:
                listarEntidades(empresaDAO.listarTodas(), "Empresas")
                break
            case 3:
                def novoCandidato = cadastrarCandidato()
                candidatoDAO.inserirCandidato(novoCandidato)
                break
            case 4:
                def novaEmpresa = cadastrarEmpresa()
                empresaDAO.inserirEmpresa(novaEmpresa)
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


def listarEntidades(List entidades, String tipo) {
    println "$tipo:"
    entidades.each { entidade ->
        println entidade.toString()
    }
}

def cadastrarCandidato() {
    println "Cadastro de Novo Candidato"
    def scanner = new Scanner(System.in)

    def nome = lerInput(scanner, "Digite o nome:")
    def sobrenome = lerInput(scanner, "Digite o sobrenome:")
    def email = lerInput(scanner, "Digite o email:")
    def descricao = lerInput(scanner, "Digite sua descrição:")
    def cpf = lerInput(scanner, "Digite o CPF:")
    def dataNascimento = lerInput(scanner, "Digite a data de nascimento (yyyy-MM-dd):")
    def pais = lerInput(scanner, "Digite o país:")
    def cep = lerInput(scanner, "Digite o CEP:")

    return new Candidato(nome, sobrenome, dataNascimento, email, cpf, pais, cep, descricao)
}

def cadastrarEmpresa() {
    println "Cadastro de Nova Empresa"
    def scanner = new Scanner(System.in)

    def nome = lerInput(scanner, "Digite o nome da empresa:")
    def email = lerInput(scanner, "Digite o email corporativo:")
    def descricao = lerInput(scanner, "Digite a descrição:")
    def competenciasStr = lerInput(scanner, "Digite as competências desejadas (separadas por vírgula):")
    List<String> competencias = competenciasStr.split(',').collect { it.trim() }
    def cnpj = lerInput(scanner, "Digite o CNPJ:")
    def pais = lerInput(scanner, "Digite o país:")
    def estado = lerInput(scanner, "Digite o estado:")
    def cep = lerInput(scanner, "Digite o CEP:")

    return new Empresa(nome, email, descricao, competencias, cnpj, pais, estado, cep)
}

def lerInput(Scanner scanner, String mensagem) {
    println mensagem
    return scanner.nextLine()
}


def exibirMatches() {
    println "Funcionalidade de exibir matches ainda não implementada."
}

menu()
