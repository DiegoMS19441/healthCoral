package br.com.fiap.healthCoral.controller;

import br.com.fiap.healthCoral.dto.camera.AtualizarCameraDto;
import br.com.fiap.healthCoral.dto.camera.CadastrarCameraDto;
import br.com.fiap.healthCoral.dto.camera.DetalhesCameraDto;
import br.com.fiap.healthCoral.dto.camera.ListagemCameraDto;
import br.com.fiap.healthCoral.model.Camera;
import br.com.fiap.healthCoral.repository.CameraRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("camera")
public class CameraController {

    @Autowired
    CameraRepository cameraRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesCameraDto> post(@RequestBody @Valid CadastrarCameraDto cameraDto,
                                                   UriComponentsBuilder uriBuilder){
        var camera = new Camera(cameraDto);
        cameraRepository.save(camera);
        var uri = uriBuilder.path("/camera/{id}").buildAndExpand(camera.getId()).toUri();
        log.info("Cadastrando camera: {}", camera);
        return ResponseEntity.created(uri).body(new DetalhesCameraDto(camera));
    }

    @GetMapping("{id}")
    public ResponseEntity<DetalhesCameraDto> get(@PathVariable("id") Long id) {
        var camera = cameraRepository.getReferenceById(id);
        log.info("Obtendo camera: {}", camera);
        return ResponseEntity.ok(new DetalhesCameraDto(camera));
    }

    @GetMapping
    public ResponseEntity<List<ListagemCameraDto>> get(Pageable pageable) {
        var listaDto = cameraRepository.findAll(pageable)
                .stream().map(ListagemCameraDto::new).toList();
        return ResponseEntity.ok(listaDto);

    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesCameraDto> put(@PathVariable("id") Long id,
                                                  @RequestBody AtualizarCameraDto dto) {
        var camera = cameraRepository.getReferenceById(id);
        camera.atualizarCamera(dto);
        log.info("Atualizando dados da camera: {}", camera);
        return ResponseEntity.ok(new DetalhesCameraDto(camera));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        cameraRepository.deleteById(id);
        log.info("Deletando camera: {}", id);
        return ResponseEntity.noContent().build();
    }
}
