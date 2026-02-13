package com.github.cidarosa.ms.produto.service;

import com.github.cidarosa.ms.produto.dto.ProdutoRequestDTO;
import com.github.cidarosa.ms.produto.dto.ProdutoResponseDTO;
import com.github.cidarosa.ms.produto.entities.Produto;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    public ProdutoResponseDTO createProduto(ProdutoRequestDTO dto){
        ProdutoResponseDTO responseDTO = new ProdutoResponseDTO(1L, dto.getNome(), dto.getDescricao(), dto.getValor());
        return responseDTO;
    }
}
