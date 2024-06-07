package br.com.fiap.healthCoral.model;

import br.com.fiap.healthCoral.dto.camera.AtualizarCameraDto;
import br.com.fiap.healthCoral.dto.camera.CadastrarCameraDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_CAMERA")
@Getter@Setter
@NoArgsConstructor
public class Camera {

    @Id @GeneratedValue
    @Column(name = "id_camera")
    private Long id;

    @Column(name = "dt_inicio_utilizacao")
    private LocalDateTime utilizacao;


    //Relacionameto ManyToMany com usuário: Muitas cameras podem ser acessadas por um usuário.
    @ManyToOne
    @JoinColumn(name="id_usuario", nullable = false)
    private Usuario usuario;




    public Camera(CadastrarCameraDto cameraDto) {
        utilizacao = cameraDto.utilizacao();

    }

    public void atualizarCamera(AtualizarCameraDto dto){
        if (dto.utilizacao() != null)
            utilizacao = dto.utilizacao();
    }
    public Camera(CadastrarCameraDto cameraDto, Usuario usuario) {
        this.utilizacao = cameraDto.utilizacao();
        this.usuario = usuario;
    }

}
