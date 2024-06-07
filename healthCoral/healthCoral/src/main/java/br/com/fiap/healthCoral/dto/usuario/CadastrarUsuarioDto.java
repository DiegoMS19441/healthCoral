package br.com.fiap.healthCoral.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CadastrarUsuarioDto(


        @NotNull@NotBlank
        String nome,

        @Email@NotNull@NotBlank
        @Pattern(regexp = "\\S+", message = "O campo e-mail não deve conter espaços")
        String email,

        @NotNull@NotBlank
        String senha) {

}
