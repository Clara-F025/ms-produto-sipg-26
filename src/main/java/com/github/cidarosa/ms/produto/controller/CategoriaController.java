package com.github.cidarosa.ms.produto.controller;

import com.github.cidarosa.ms.produto.dto.CategoriaDTO;
import com.github.cidarosa.ms.produto.dto.ProdutoDTO;
import com.github.cidarosa.ms.produto.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
        service.deleteCategoriaById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/uri")
    public ResponseEntity<CategoriaDTO> createCategoriaUri(@RequestBody @Valid CategoriaDTO dto) {
        CategoriaDTO categoriaDTO = service.saveCategoria(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("{/id}")
                .buildAndExpand(categoriaDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).body(categoriaDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> updateCategoria(@PathVariable Long id, @RequestBody @Valid CategoriaDTO dto) {
        return ResponseEntity.ok(service.updateCategoria(id, dto));
    }


    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findCategoriaById(id));
    }

    @GetMapping("/lista")
    public ResponseEntity<List<CategoriaDTO>> getCategorias() {
        List<CategoriaDTO> dto = service.findAllCategorias();
        return ResponseEntity.ok(dto);
    }
}
