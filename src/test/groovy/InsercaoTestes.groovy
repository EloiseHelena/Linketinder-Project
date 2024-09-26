import spock.lang.Specification
import candidatos.Candidato
import empresa.Empresa


class InsercaoTestes extends Specification {

    def "Deve inserir candidato na lista de candidatos"() {
        given: "Uma lista vazia de candidatos"
        def candidatos = []

        and: "Um novo candidato"
        def candidato = new Candidato("Eloise", "eloise@exemplo.com", "Desenvolvedor", ["Java"], "11122233344", 30, "RS", "01000-000")

        when: "Inserimos o candidato na lista"
        candidatos << candidato

        then: "A lista deve ter exatamente 1 candidato"
        candidatos.size() == 1

        and: "O candidato inserido deve ser o correto"
        candidatos[0].nome == "Eloise"
        candidatos[0].cpf == "11122233344"
    }

    def "Deve inserir empresa na lista de empresas"() {
        given: "Uma lista vazia de empresas"
        def empresas = []

        and: "Uma nova empresa"
        def empresa = new Empresa("Inova Tech", "contato@inovatech.com", "Empresa de Inovação", ["Tecnologia"], "12345678000100", "Brasil", "SP", "02000-000")

        when: "Inserimos a empresa na lista"
        empresas << empresa

        then: "A lista deve ter exatamente 1 empresa"
        empresas.size() == 1

        and: "A empresa inserida deve ser a correta"
        empresas[0].nome == "Inova Tech"
        empresas[0].cnpj == "12345678000100"
    }
}
