package br.com.fiap.healthCoral.dto.coral;

import br.com.fiap.healthCoral.model.Coral;

public record DetalhesCoralDto(String nome, String descricao) {

    public DetalhesCoralDto(Coral coral){
        this(coral.getNome(),coral.getDescricao());
    }
}
