package candidatos

import org.springframework.data.repository.CrudRepository

interface CandidatoRepository extends CrudRepository<Candidato, Long> {
    Candidato findByEmailAndSenha(String email, String senha)
}

