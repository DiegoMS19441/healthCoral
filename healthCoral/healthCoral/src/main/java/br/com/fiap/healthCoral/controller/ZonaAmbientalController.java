package br.com.fiap.healthCoral.controller;

import br.com.fiap.healthCoral.dto.zonaAmbiental.AtualizarZonaAmbientalDto;
import br.com.fiap.healthCoral.dto.zonaAmbiental.CadastrarZonaAmbientalDto;
import br.com.fiap.healthCoral.dto.zonaAmbiental.DetalhesZonaAmbientalDto;

import br.com.fiap.healthCoral.dto.zonaAmbiental.ListagemZonaAmbientalDto;
import br.com.fiap.healthCoral.model.ZonaAmbiental;
import br.com.fiap.healthCoral.repository.ZonaAmbientalRepository;
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
@Slf4j
@RequestMapping("zona")
public class ZonaAmbientalController {

    @Autowired
    private ZonaAmbientalRepository zonaAmbientalRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesZonaAmbientalDto> post(@RequestBody @Valid CadastrarZonaAmbientalDto zonaDto,
                                                         UriComponentsBuilder uriBuilder){
        var zonaAmbiental = new ZonaAmbiental(zonaDto);
        zonaAmbientalRepository.save(zonaAmbiental);
        var uri = uriBuilder.path("/user/{id}").buildAndExpand(zonaAmbiental.getId()).toUri();
        log.info("Cadastrando uma nova zona ambiental: {}", zonaAmbiental);
        return ResponseEntity.created(uri).body(new DetalhesZonaAmbientalDto(zonaAmbiental));
    }

    @GetMapping("{id}")
    public ResponseEntity<DetalhesZonaAmbientalDto> get(@PathVariable("id") Long id) {
        var zonaAmbiental = zonaAmbientalRepository.getReferenceById(id);
        log.info("Obtendo usuario: {}", zonaAmbiental);
        return ResponseEntity.ok(new DetalhesZonaAmbientalDto(zonaAmbiental));
    }

    @GetMapping
    public ResponseEntity<List<ListagemZonaAmbientalDto>> get(Pageable pageable) {
        var listaDto = zonaAmbientalRepository.findAll(pageable)
                .stream().map(ListagemZonaAmbientalDto::new).toList();
        return ResponseEntity.ok(listaDto);

    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesZonaAmbientalDto> put(@PathVariable("id") Long id,
                                                  @RequestBody AtualizarZonaAmbientalDto dto) {
        var zonaAmbiental = zonaAmbientalRepository.getReferenceById(id);
        zonaAmbiental.atualizarZona(dto);
        log.info("Atualizando a zona ambiental informada : {}", zonaAmbiental);
        return ResponseEntity.ok(new DetalhesZonaAmbientalDto(zonaAmbiental));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        zonaAmbientalRepository.deleteById(id);
        log.info("Deletando a zona ambiental cadastrada: {}", id);
        return ResponseEntity.noContent().build();
    }


}
