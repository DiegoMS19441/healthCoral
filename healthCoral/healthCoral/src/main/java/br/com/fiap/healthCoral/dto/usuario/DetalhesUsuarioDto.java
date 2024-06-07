package br.com.fiap.healthCoral.dto.usuario;

import br.com.fiap.healthCoral.model.Usuario;

public record DetalhesUsuarioDto(Long id, String nome, String email, String senha) {

    public DetalhesUsuarioDto(Usuario usuario){
        this(usuario.getId(),usuario.getNome(),usuario.getEmail(),usuario.getSenha());
    }
}
