package br.com.fiap.healthCoral.dto.camera;

import br.com.fiap.healthCoral.model.Camera;

import java.time.LocalDateTime;

public record ListagemCameraDto(Long id, LocalDateTime utilizacao) {

    public ListagemCameraDto(Camera camera){
        this(camera.getId(), camera.getUtilizacao());
    }
}
