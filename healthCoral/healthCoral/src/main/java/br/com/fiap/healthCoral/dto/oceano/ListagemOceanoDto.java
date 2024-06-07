package br.com.fiap.healthCoral.dto.oceano;

import br.com.fiap.healthCoral.model.Oceano;

public record ListagemOceanoDto(Long id, String continente) {

    public ListagemOceanoDto(Oceano oceano) {
        this (oceano.getId(), oceano.getContinente());

    }
}
