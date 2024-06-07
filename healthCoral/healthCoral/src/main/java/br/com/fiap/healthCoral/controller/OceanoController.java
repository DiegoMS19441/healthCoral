package br.com.fiap.healthCoral.controller;

import br.com.fiap.healthCoral.dto.oceano.AtualizarOceanoDto;
import br.com.fiap.healthCoral.dto.oceano.CadastrarOceanoDto;
import br.com.fiap.healthCoral.dto.oceano.DetalhesOceanoDto;
import br.com.fiap.healthCoral.dto.oceano.ListagemOceanoDto;
import br.com.fiap.healthCoral.model.Oceano;
import br.com.fiap.healthCoral.repository.OceanoRepository;
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
@RequestMapping("oceano")
@Slf4j
public class OceanoController {

    @Autowired
    OceanoRepository oceanoRepository;

    public OceanoController(OceanoRepository oceanoRepository) {
        this.oceanoRepository = oceanoRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesOceanoDto> post(@RequestBody @Valid CadastrarOceanoDto oceanoDto,
                                                  UriComponentsBuilder uriBuilder){
        var oceano = new Oceano(oceanoDto);
        oceanoRepository.save(oceano);
        var uri = uriBuilder.path("/oceano/{id}").buildAndExpand(oceano.getId()).toUri();
        log.info("Cadastrando um oceano: {}", oceano);
        return ResponseEntity.created(uri).body(new DetalhesOceanoDto(oceano));
    }

    @GetMapping("{id}")
    public ResponseEntity<DetalhesOceanoDto> get(@PathVariable("id") Long id) {
        var oceano= oceanoRepository.getReferenceById(id);
        log.info("Obtendo oceano cadastrado: {}", oceano);
        return ResponseEntity.ok(new DetalhesOceanoDto(oceano));
    }

    @GetMapping
    public ResponseEntity<List<ListagemOceanoDto>> get(Pageable pageable) {
        var listaDto = oceanoRepository.findAll(pageable)
                .stream().map(ListagemOceanoDto::new).toList();
        return ResponseEntity.ok(listaDto);

    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesOceanoDto> put(@PathVariable("id") Long id,
                                                  @RequestBody AtualizarOceanoDto dto) {
        var oceano = oceanoRepository.getReferenceById(id);
        oceano.atualizarOceano(dto);
        log.info("Atualizando oceano cadastrado: {}", oceano);
        return ResponseEntity.ok(new DetalhesOceanoDto(oceano));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        oceanoRepository.deleteById(id);
        log.info("Deletando oceano informado: {}", id);
        return ResponseEntity.noContent().build();
    }

}
