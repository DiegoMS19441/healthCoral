package br.com.fiap.healthCoral.dto.oceano;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record CadastrarOceanoDto(


        @NotNull@NotBlank
        String continente) {
}
