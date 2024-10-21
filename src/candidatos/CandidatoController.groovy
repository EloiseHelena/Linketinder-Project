package candidatos
import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus

@RestController
@RequestMapping("/api/candidato")
class CandidatoController {

    @PostMapping("/cadastro")
    ResponseEntity<?> cadastrarCandidato(@RequestBody Candidato candidato) {
        try {
            candidatoService.salvarCandidato(candidato)
            return new ResponseEntity<>("Candidato cadastrado com sucesso!", HttpStatus.CREATED)
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao cadastrar candidato: ${e.message}", HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping("/login")
    ResponseEntity<?> loginCandidato(@RequestBody LoginRequest loginRequest) {
        try {
            Candidato candidato = candidatoService.buscarPorEmailESenha(loginRequest.email, loginRequest.senha)
            if (candidato) {
                return new ResponseEntity<>("Login realizado com sucesso!", HttpStatus.OK)
            } else {
                return new ResponseEntity<>("Email ou senha incorretos.", HttpStatus.UNAUTHORIZED)
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Erro no login: ${e.message}", HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}

