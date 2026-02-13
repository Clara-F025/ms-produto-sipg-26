package com.github.cidarosa.ms.produto.service;

import com.github.cidarosa.ms.produto.dto.ProdutoDTO;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    public ProdutoDTO createProduto(ProdutoDTO dto) {
        ProdutoDTO responseDTO = new ProdutoDTO(1L, dto.getNome(), dto.getDescricao(), dto.getValor());
        return responseDTO;
    }
}
