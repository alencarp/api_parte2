package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.usuario.DadosAutenticacao;
import med.voll.api.domain.usuario.Usuario;
import med.voll.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;


    // preciso chamar o método AutenticacaoService.loadUserByUsername(String username) que faz a autenticação
    //Porém, no Spring não chamamos direto a classe, mas chamamos a AutenticationManager, que por baixo dos panos vai chamar ela
    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {

        //converte do nosso dto para o UsernamePasswordAuthenticationToken(que é tipo um dto do Spring)
        var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());

        //retorna um objeto que representa o usuario autenticado
        Authentication authentication = manager.authenticate(token);

        //Para usar JWT pegamos no site A Auth0 (biblioteca em Java para gerar tokens em JWT)


        return ResponseEntity.ok(tokenService.gerarToken((Usuario) authentication.getPrincipal()));
    }
}
