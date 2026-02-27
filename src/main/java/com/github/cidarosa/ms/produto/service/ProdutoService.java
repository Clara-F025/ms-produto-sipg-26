package com.github.cidarosa.ms.produto.service;

import com.github.cidarosa.ms.produto.dto.ProdutoDTO;
import com.github.cidarosa.ms.produto.entities.Produto;
import com.github.cidarosa.ms.produto.exceptions.ResourceNotFoundException;
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

    @Transactional
    public void deleteProdutoById(Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado, ID:" + id);
        }
        repository.deleteById(id);
    }

    @Transactional
    public ProdutoDTO saveProduto(ProdutoDTO produtoDTO) {
        Produto produto = new Produto();

        copyToDTOProduto(produto, produtoDTO);
        produto = repository.save(produto);
        return new ProdutoDTO(produto);
    }

    @Transactional
    public ProdutoDTO updateProduto(Long id, ProdutoDTO produtoDTO) {
        try {
            Produto produto = repository.getReferenceById(id);
            copyToDTOProduto(produto, produtoDTO);
            produto = repository.save(produto);
            return new ProdutoDTO(produto);
        } catch (EntityNotFoundException ex) {
            throw new ResourceNotFoundException("Recurso não encontrado, ID:" + id);
        }
    }

    private void copyToDTOProduto(Produto produto, ProdutoDTO produtoDTO) {
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setNome(produtoDTO.getNome());
        produto.setValor(produtoDTO.getValor());
    }

    @Transactional(readOnly = true)
    public List<ProdutoDTO> findAllProdutos() {
        List<Produto> produtos = repository.findAll();
        return produtos.stream().map(ProdutoDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public ProdutoDTO findProdutoById(Long id) {
        return new ProdutoDTO(repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado. ID: " + id)));

    }
}
