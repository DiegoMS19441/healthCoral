package br.com.fiap.healthCoral.controller;

import br.com.fiap.healthCoral.dto.login.DadosLoginDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticacao")
public class LoginController {
    @PostMapping("/login")
    public ResponseEntity<DadosLoginDto> post() {
        var login = new DadosLoginDto("Georgina");
        return ResponseEntity.ok(login);
    }
}
