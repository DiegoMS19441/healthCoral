package br.com.fiap.healthCoral.dto.zonaAmbiental;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CadastrarZonaAmbientalDto(

        @NotNull
        BigDecimal area,

        @Size(max = 350)
        String descricao) {
}
