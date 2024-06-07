package br.com.fiap.healthCoral.controller;

import br.com.fiap.healthCoral.dto.foto.AtualizarFotoDto;
import br.com.fiap.healthCoral.dto.foto.DetalhesFotoDto;
import br.com.fiap.healthCoral.dto.foto.ListagemFotoDto;
import br.com.fiap.healthCoral.dto.foto.NovaFotoDto;
import br.com.fiap.healthCoral.model.Foto;
import br.com.fiap.healthCoral.repository.FotoRepository;
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
@RequestMapping("foto")
@Slf4j


public class FotoController {



        @Autowired
        FotoRepository fotoRepository;

        @PostMapping
        @Transactional
        public ResponseEntity<DetalhesFotoDto> post(@RequestBody @Valid NovaFotoDto fotoDto,
                                                    UriComponentsBuilder uriBuilder){
            var foto = new Foto(fotoDto);
            fotoRepository.save(foto);
            var uri = uriBuilder.path("/foto/{id}").buildAndExpand(foto.getId()).toUri();
            log.info("Tirando uma nova foto: {}", foto);
            return ResponseEntity.created(uri).body(new DetalhesFotoDto(foto));
        }

        @GetMapping("{id}")
        public ResponseEntity<DetalhesFotoDto> get(@PathVariable("id") Long id) {
            var foto = fotoRepository.getReferenceById(id);
            log.info("Obtendo foto tirada: {}", foto);
            return ResponseEntity.ok(new DetalhesFotoDto(foto));
        }

        @GetMapping
        public ResponseEntity<List<ListagemFotoDto>> get(Pageable pageable) {
            var listaDto = fotoRepository.findAll(pageable)
                    .stream().map(ListagemFotoDto::new).toList();
            return ResponseEntity.ok(listaDto);

        }

       @PutMapping("{id}")
       @Transactional
       public ResponseEntity<DetalhesFotoDto> put(@PathVariable("id") Long id,
                                                     @RequestBody AtualizarFotoDto dto) {
           var foto = fotoRepository.getReferenceById(id);
           foto.atualizarFoto(dto);
           log.info("Atualizando foto: {}", foto);
           return ResponseEntity.ok(new DetalhesFotoDto(foto));
       }

        @DeleteMapping("{id}")
        @Transactional
        public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
            fotoRepository.deleteById(id);
            log.info("Deletando foto: {}", id);
            return ResponseEntity.noContent().build();
        }
}
