package br.com.fiap.healthCoral.dto.camera;

import br.com.fiap.healthCoral.dto.usuario.DetalhesUsuarioDto;
import br.com.fiap.healthCoral.model.Camera;
import br.com.fiap.healthCoral.model.Usuario;

import java.time.LocalDateTime;

public record DetalhesCameraDto(Long id, LocalDateTime utilizacao, DetalhesUsuarioDto detalhesUsuarioDto) {

    public DetalhesCameraDto(Camera camera){
        this(camera.getId(), camera.getUtilizacao(), new DetalhesUsuarioDto(camera.getUsuario()));
    }
}
