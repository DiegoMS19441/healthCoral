package br.com.fiap.healthCoral.dto.zonaAmbiental;

import br.com.fiap.healthCoral.model.ZonaAmbiental;

import java.math.BigDecimal;

public record ListagemZonaAmbientalDto(Long id, BigDecimal area, String descricao) {

    public ListagemZonaAmbientalDto(ZonaAmbiental zonaAmbiental){
        this(zonaAmbiental.getId(), zonaAmbiental.getArea(), zonaAmbiental.getDescricao());
    }
}
