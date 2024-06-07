package br.com.fiap.healthCoral.dto.foto;

import br.com.fiap.healthCoral.model.Foto;

import java.time.LocalDateTime;

public record DetalhesFotoDto(LocalDateTime captura) {

    public DetalhesFotoDto(Foto foto) {
        this(foto.getCaptura());
    }
}

