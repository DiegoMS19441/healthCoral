package br.com.fiap.healthCoral.dto.oceano;

import br.com.fiap.healthCoral.model.Oceano;

public record DetalhesOceanoDto(Long id, String continente) {

    public DetalhesOceanoDto(Oceano oceano) {
        this(oceano.getId(), oceano.getContinente());
    }
}
