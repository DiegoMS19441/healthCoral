package br.com.fiap.healthCoral.model;

import br.com.fiap.healthCoral.dto.usuario.AtualizarUsuarioDto;
import br.com.fiap.healthCoral.dto.usuario.CadastrarUsuarioDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "TB_USUARIO")
@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue
    @Column(name = "id_usuario")
    private Long id;

    @Column(name = "nm_usuario")
    private String nome;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_senha")
    private String senha;


    //Relacionamento OneToMany com câmera: Um usuário pode acessar várias câmeras
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
    private List<Camera> cameras;


    public Usuario(CadastrarUsuarioDto usuarioDto) {
        nome = usuarioDto.nome();
        email = usuarioDto.email();
        senha = usuarioDto.senha();
    }

    public void atualizarUsuario(AtualizarUsuarioDto dto) {
        if (dto.nome() != null)
            nome = dto.nome();
        if (dto.email() != null)
            email = dto.email();
        if (dto.senha() != null)
            senha = dto.senha();
    }


}
