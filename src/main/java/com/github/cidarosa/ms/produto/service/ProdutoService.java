package com.github.cidarosa.ms.produto.service;

import com.github.cidarosa.ms.produto.dto.ProdutoDTO;
import com.github.cidarosa.ms.produto.entities.Produto;
import com.github.cidarosa.ms.produto.repositories.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    public ProdutoRepository repository;

    public ProdutoDTO createProduto(ProdutoDTO dto) {
        ProdutoDTO responseDTO = new ProdutoDTO(1L, dto.getNome(), dto.getDescricao(), dto.getValor());
        return responseDTO;
    }

    @Transactional(readOnly = true)
    public List<ProdutoDTO> findAllProdutos(){
        List<Produto> produtos = repository.findAll();
        return produtos.stream().map(ProdutoDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public ProdutoDTO findProdutoById(Long id){
        return new ProdutoDTO(repository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("Recurso não encontrado. ID: "+id)));

    }
}
