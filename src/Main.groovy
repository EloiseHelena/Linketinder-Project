package src

import candidatos.Candidato
import empresa.Empresa
import dao.CandidatoDAO
import dao.EmpresaDAO
import java.util.ArrayList;
import java.util.List;

class Main {
    static CandidatoDAO candidatoDAO = new CandidatoDAO()
    static EmpresaDAO empresaDAO = new EmpresaDAO()

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

            Scanner option = new Scanner(System.in).nextInt()
            switch (option) {
                case 1:
                    listarEntidades(candidatoDAO.listarTodos(), "Candidatos")
                    break
                case 2:
                    listarEntidades(empresaDAO.listarTodas(), "Empresas")
                    break
                case 3:
                    Candidato novoCandidato = cadastrarCandidato()
                    candidatoDAO.cadastrarCandidato(novoCandidato)
                    break
                case 4:
                    Empresa novaEmpresa = cadastrarEmpresa()
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
        // TODO
        //  listar competencias (IMPLEMENTAR)
        //  Imprimir competencias
        //  String competencias = lerInput(scanner, "Digite suas competencias:")
        //  Validar se na lista de competencias existe a competencia do usuário
        //  Adicionar ao construtor do método Candidato a lista de Competencias




        println(nome + sobrenome + dataNascimento + email + cpf + pais + cep + descricao)

        return new Candidato(nome, sobrenome, dataNascimento, email, cpf, pais, cep, descricao)
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

        List<String> GAMBIARRA = new ArrayList<>();
        GAMBIARRA.add("C");
        return new Empresa(nome, email, descricao, GAMBIARRA, cnpj, pais, estado, cep)
    }


    static String lerInput(Scanner scanner, String mensagem) {
        println mensagem
        return scanner.nextLine()
    }


    def exibirMatches() {
        println "Funcionalidade de exibir matches ainda não implementada."
    }

    static void main(String[] args) {
        menu()

    }

}





