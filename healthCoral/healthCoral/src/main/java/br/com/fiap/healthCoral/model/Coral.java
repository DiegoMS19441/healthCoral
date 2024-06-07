package br.com.fiap.healthCoral.model;

import br.com.fiap.healthCoral.dto.coral.AtualizarCoralDto;
import br.com.fiap.healthCoral.dto.coral.CadastrarCoralDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_CORAL")
@Getter@Setter
@NoArgsConstructor
public class Coral {

    @Id
    @GeneratedValue
    @Column(name = "id_coral")
    private Long id;

    @Column(name="nm_coral")
    private String nome;

    @Column(name = "des_coral")
    private String descricao;

    @ManyToMany
    @JoinTable(name = "TB_CORAL_FOTO",
            joinColumns = @JoinColumn(name = "id_coral"),
            inverseJoinColumns = @JoinColumn(name="id_"))
    private List<Foto> fotos;

    public Coral(CadastrarCoralDto coralDto) {
        nome = coralDto.nome();
        descricao = coralDto.descricao();
    }

    public void atualizarCoral(AtualizarCoralDto dto){
        if (dto.nome()!=null)
            nome = dto.nome();
        if (dto.descricao()!=null)
            descricao = dto.descricao();

    }

}
