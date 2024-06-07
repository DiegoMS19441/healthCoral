package br.com.fiap.healthCoral.dto.coral;

import br.com.fiap.healthCoral.model.Coral;

public record ListagemCoralDto(Long id, String nome, String descricao) {

    public ListagemCoralDto(Coral coral){
        this(coral.getId(),coral.getNome(),coral.getDescricao());
    }

}
