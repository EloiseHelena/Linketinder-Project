package candidatos
import javax.persistence.*

@Entity
class Candidato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    String nome
    String sobrenome
    String dataNascimento
    String email
    String cpf
    String pais
    String cep
    String descricao


    Candidato(String nome, String sobrenome, String dataNascimento, String email, String cpf, String pais, String cep, String descricao) {
        this.nome = nome
        this.sobrenome = sobrenome
        this.dataNascimento = dataNascimento
        this.email = email
        this.cpf = cpf
        this.pais = pais
        this.cep = cep
        this.descricao = descricao
    }
}
