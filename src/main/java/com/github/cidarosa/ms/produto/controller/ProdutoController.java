package com.github.cidarosa.ms.produto.controller;

import com.github.cidarosa.ms.produto.dto.ProdutoDTO;
import com.github.cidarosa.ms.produto.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    public ProdutoService service;

    @Profile("test")
    @GetMapping("/--demo/500")
    public String force500(){
        throw new RuntimeException("Erro 500 forçado para demonstração");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
        service.deleteProdutoById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/uri")
    public ResponseEntity<ProdutoDTO> createProdutoUri(@RequestBody @Valid ProdutoDTO dto) {
        ProdutoDTO produtoDTO = service.saveProduto(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("{/id}")
                .buildAndExpand(produtoDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).body(produtoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> updateProduto(@PathVariable Long id, @RequestBody @Valid ProdutoDTO dto) {
        return ResponseEntity.ok(service.updateProduto(id, dto));
    }

    @GetMapping("/lista")
    public ResponseEntity<List<ProdutoDTO>> getProdutos() {
        List<ProdutoDTO> dto = service.findAllProdutos();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findProdutoById(id));
    }
}
