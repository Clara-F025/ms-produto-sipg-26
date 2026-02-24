package com.github.cidarosa.ms.produto.controller;

import com.github.cidarosa.ms.produto.dto.ProdutoDTO;
import com.github.cidarosa.ms.produto.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id){
        service.deleteProdutoById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/uri")
    public ResponseEntity<ProdutoDTO> createProdutoUri(@RequestBody ProdutoDTO dto){
         ProdutoDTO produtoDTO = service.saveProduto(dto);
         URI uri = ServletUriComponentsBuilder
                 .fromCurrentRequestUri()
                 .path("{/id}")
                 .buildAndExpand(produtoDTO.getId())
                 .toUri();

     return ResponseEntity.created(uri).body(produtoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> createProduto(@PathVariable Long id, @RequestBody ProdutoDTO dto){
        return ResponseEntity.ok(service.updateProduto(id, dto));
    }

    @GetMapping("/lista")
    public ResponseEntity<List<ProdutoDTO>> getProdutos() {
        List<ProdutoDTO> dto = service.findAllProdutos();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findProdutoById(id));
    }

//    @PostMapping
//    public ResponseEntity<ProdutoDTO> createProduto(@RequestBody ProdutoDTO dto) {
//        return ResponseEntity.ok(service.createProduto(dto));
//

    }

//        produtos.add(new Produto(1L, "Smart Tv", "Smart tv lg 50 polegadas", 2285.0));
//        produtos.add(new Produto(2L, "Iphone 15 plus", "Iphone 15 plus apple", 5000.0));
//        produtos.add(new Produto(3L, "Mouse sem fio", "Mouse sem fio Logitech", 220.0));


