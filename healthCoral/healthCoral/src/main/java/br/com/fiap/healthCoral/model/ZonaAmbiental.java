package br.com.fiap.healthCoral.model;

import br.com.fiap.healthCoral.dto.zonaAmbiental.AtualizarZonaAmbientalDto;
import br.com.fiap.healthCoral.dto.zonaAmbiental.CadastrarZonaAmbientalDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "TB_ZONA_AMBIENTAL")
@Getter@Setter
@NoArgsConstructor
public class ZonaAmbiental {


    @Id
    @GeneratedValue
    @Column(name = "id_zona")
    private Long id;

    @Column(name = "area_zona")
    private BigDecimal area;


    @Column(name="des_zona")
    private String descricao;


    public ZonaAmbiental(CadastrarZonaAmbientalDto zonaDto) {
        area = zonaDto.area();
        descricao = zonaDto.descricao();
    }

    public void atualizarZona(AtualizarZonaAmbientalDto dto) {
        if (dto.area() != null)
            area = dto.area();
        if (dto.descricao() != null)
            descricao = dto.descricao();
    }
}
