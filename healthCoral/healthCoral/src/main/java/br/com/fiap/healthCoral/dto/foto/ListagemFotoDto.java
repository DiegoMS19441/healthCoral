package br.com.fiap.healthCoral.dto.foto;

import br.com.fiap.healthCoral.model.Foto;

import java.time.LocalDateTime;

public record ListagemFotoDto (LocalDateTime captura){

    public ListagemFotoDto(Foto foto) {
        this(foto.getCaptura());
    }
}
