package com.github.cidarosa.ms.produto.controller;

import com.github.cidarosa.ms.produto.dto.ProdutoDTO;
import com.github.cidarosa.ms.produto.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    public ProdutoService service;

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> getProduto() {
        List<ProdutoDTO> dto = ProdutoDTO.createMock();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> createProduto(@RequestBody ProdutoDTO dto) {
        return ResponseEntity.ok(service.createProduto(dto));


    }

//        produtos.add(new Produto(1L, "Smart Tv", "Smart tv lg 50 polegadas", 2285.0));
//        produtos.add(new Produto(2L, "Iphone 15 plus", "Iphone 15 plus apple", 5000.0));
//        produtos.add(new Produto(3L, "Mouse sem fio", "Mouse sem fio Logitech", 220.0));

}
