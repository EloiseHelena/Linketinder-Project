package candidatos

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CandidatoService {

    @Autowired
    CandidatoRepository candidatoRepository

    void salvarCandidato(Candidato candidato) {
        candidatoRepository.save(candidato)
    }

    Candidato buscarPorEmailESenha(String email, String senha) {
        return candidatoRepository.findByEmailAndSenha(email, senha)
    }

}
