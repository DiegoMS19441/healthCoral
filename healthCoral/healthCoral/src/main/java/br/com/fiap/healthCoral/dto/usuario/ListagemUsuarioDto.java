package br.com.fiap.healthCoral.dto.usuario;

import br.com.fiap.healthCoral.model.Usuario;

public record ListagemUsuarioDto(Long id, String nome, String email, String senha) {

    public ListagemUsuarioDto(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha());
    }
}
