package br.com.fiap.healthCoral.dto.camera;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CadastrarCameraDto(

        @NotNull
        LocalDateTime utilizacao) {
}
