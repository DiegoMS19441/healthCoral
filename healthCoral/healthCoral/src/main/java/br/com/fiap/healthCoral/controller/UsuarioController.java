package br.com.fiap.healthCoral.controller;

import br.com.fiap.healthCoral.dto.camera.CadastrarCameraDto;
import br.com.fiap.healthCoral.dto.camera.DetalhesCameraDto;
import br.com.fiap.healthCoral.dto.usuario.AtualizarUsuarioDto;
import br.com.fiap.healthCoral.dto.usuario.CadastrarUsuarioDto;
import br.com.fiap.healthCoral.dto.usuario.DetalhesUsuarioDto;
import br.com.fiap.healthCoral.dto.usuario.ListagemUsuarioDto;
import br.com.fiap.healthCoral.model.Camera;
import br.com.fiap.healthCoral.model.Usuario;
import br.com.fiap.healthCoral.repository.CameraRepository;
import br.com.fiap.healthCoral.repository.UsuarioRepository;
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
@RequestMapping("usuario")
@Slf4j
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    CameraRepository CameraRepository;
    @Autowired
    private CameraRepository cameraRepository;



    @PostMapping("{id}/camera")
    @Transactional
    public ResponseEntity<DetalhesCameraDto> post(@PathVariable("id") Long id,
                                                  @RequestBody @Valid CadastrarCameraDto dto,
                                                  UriComponentsBuilder uriBuilder){
        var usuario = usuarioRepository.getReferenceById(id);
        var camera = new Camera(dto, usuario);
        cameraRepository.save(camera);
        var uri = uriBuilder.path("camera/{id}").buildAndExpand(camera.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesCameraDto(camera));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesUsuarioDto> post(@RequestBody @Valid CadastrarUsuarioDto usuarioDto,
                                                   UriComponentsBuilder uriBuilder){
        var usuario = new Usuario(usuarioDto);
        usuarioRepository.save(usuario);
        var uri = uriBuilder.path("/user/{id}").buildAndExpand(usuario.getId()).toUri();
        log.info("Cadastrando usuario: {}", usuario);
        return ResponseEntity.created(uri).body(new DetalhesUsuarioDto(usuario));
    }

    @GetMapping("{id}")
    public ResponseEntity<DetalhesUsuarioDto> get(@PathVariable("id") Long id) {
        var usuario = usuarioRepository.getReferenceById(id);
        log.info("Obtendo usuario: {}", usuario);
        return ResponseEntity.ok(new DetalhesUsuarioDto(usuario));
    }

    @GetMapping
    public ResponseEntity<List<ListagemUsuarioDto>> get(Pageable pageable) {
        var listaDto = usuarioRepository.findAll(pageable)
                .stream().map(ListagemUsuarioDto::new).toList();
        return ResponseEntity.ok(listaDto);

    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesUsuarioDto> put(@PathVariable("id") Long id,
                                                       @RequestBody AtualizarUsuarioDto dto) {
        var usuario = usuarioRepository.getReferenceById(id);
        usuario.atualizarUsuario(dto);
        log.info("Atualizando dados do usuario: {}", usuario);
        return ResponseEntity.ok(new DetalhesUsuarioDto(usuario));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        usuarioRepository.deleteById(id);
        log.info("Deletando usuario: {}", id);
        return ResponseEntity.noContent().build();
    }


}
