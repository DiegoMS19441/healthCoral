package br.com.fiap.healthCoral.model;

import br.com.fiap.healthCoral.dto.foto.AtualizarFotoDto;
import br.com.fiap.healthCoral.dto.foto.NovaFotoDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "TB_FOTO")
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Foto {

    @Id @GeneratedValue
    @Column(name = "id_foto")
    private Long id;

    @Column(name = "dt_captura")
    private LocalDateTime captura;

    @ManyToMany(mappedBy = "fotos")
    private List<Coral> corais;

    public Foto(NovaFotoDto fotoDto) {
        captura = fotoDto.captura();


    }

   public void atualizarFoto(AtualizarFotoDto dto) {
       if (dto.captura() != null)
           captura = dto.captura();
   }






}
