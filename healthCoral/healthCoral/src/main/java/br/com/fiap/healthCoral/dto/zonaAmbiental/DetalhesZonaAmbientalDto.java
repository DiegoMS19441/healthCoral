package br.com.fiap.healthCoral.dto.zonaAmbiental;

import br.com.fiap.healthCoral.model.ZonaAmbiental;

import java.math.BigDecimal;

public record DetalhesZonaAmbientalDto(Long id,BigDecimal area, String descricao) {

    public DetalhesZonaAmbientalDto(ZonaAmbiental zonaAmbiental){
        this(zonaAmbiental.getId(), zonaAmbiental.getArea(), zonaAmbiental.getDescricao());
    }
}
