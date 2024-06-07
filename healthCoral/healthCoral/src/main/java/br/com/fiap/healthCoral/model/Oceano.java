package br.com.fiap.healthCoral.model;



import br.com.fiap.healthCoral.dto.oceano.AtualizarOceanoDto;
import br.com.fiap.healthCoral.dto.oceano.CadastrarOceanoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_OCEANO")
@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
public class Oceano {

    @Id
    @GeneratedValue
    @Column(name = "id_oceano")
    private Long id;


    @Column(name = "des_continente")
    private String continente;

    public Oceano(CadastrarOceanoDto oceanoDto) {

        continente = oceanoDto.continente();

    }

    public void atualizarOceano(AtualizarOceanoDto dto) {
        if (dto.continente() != null)
            continente = dto.continente();
    }


}
