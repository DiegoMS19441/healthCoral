package br.com.fiap.healthCoral.controller;



import br.com.fiap.healthCoral.dto.coral.AtualizarCoralDto;
import br.com.fiap.healthCoral.dto.coral.CadastrarCoralDto;
import br.com.fiap.healthCoral.dto.coral.DetalhesCoralDto;
import br.com.fiap.healthCoral.dto.coral.ListagemCoralDto;
import br.com.fiap.healthCoral.model.Coral;
import br.com.fiap.healthCoral.repository.CoralRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("coral")
@Slf4j
public class CoralController {

    @Autowired
    CoralRepository coralRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesCoralDto> post(@RequestBody @Valid CadastrarCoralDto coralDto,
                                                   UriComponentsBuilder uriBuilder){
        var coral = new Coral(coralDto);
        coralRepository.save(coral);
        var uri = uriBuilder.path("/coral/{id}").buildAndExpand(coral.getId()).toUri();
        log.info("Cadastrando coral: {}", coral);
        return ResponseEntity.created(uri).body(new DetalhesCoralDto(coral));
    }

    @GetMapping("{id}")
    public ResponseEntity<DetalhesCoralDto> get(@PathVariable("id") Long id) {
        var coral = coralRepository.getReferenceById(id);
        log.info("Obtendo coral: {}", coral);
        return ResponseEntity.ok(new DetalhesCoralDto(coral));
    }

    @GetMapping
    public ResponseEntity<List<ListagemCoralDto>> get(Pageable pageable) {
        var listaDto = coralRepository.findAll(pageable)
                .stream().map(ListagemCoralDto::new).toList();
        return ResponseEntity.ok(listaDto);

    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesCoralDto> put(@PathVariable("id") Long id,
                                                  @RequestBody AtualizarCoralDto dto) {
        var coral = coralRepository.getReferenceById(id);
        coral.atualizarCoral(dto);
        log.info("Atualizando dados do coral: {}", coral);
        return ResponseEntity.ok(new DetalhesCoralDto(coral));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        coralRepository.deleteById(id);
        log.info("Deletando coral: {}", id);
        return ResponseEntity.noContent().build();
    }
}
