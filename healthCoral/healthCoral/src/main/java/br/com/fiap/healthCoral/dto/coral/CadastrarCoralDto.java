package br.com.fiap.healthCoral.dto.coral;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastrarCoralDto(

        @NotNull@NotBlank
        String nome,

        @NotNull@NotBlank
        String descricao) {
}
