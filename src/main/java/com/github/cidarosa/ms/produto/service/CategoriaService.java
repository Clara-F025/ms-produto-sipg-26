package com.github.cidarosa.ms.produto.service;

import com.github.cidarosa.ms.produto.dto.CategoriaDTO;
import com.github.cidarosa.ms.produto.entities.Categoria;
import com.github.cidarosa.ms.produto.exceptions.DatabaseException;
import com.github.cidarosa.ms.produto.exceptions.ResourceNotFoundException;
import com.github.cidarosa.ms.produto.repositories.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    public CategoriaRepository repository;

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteCategoriaById(Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado, ID:" + id);
        }

        try {
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Não foi possível excluir a categoria, existem itens associados a ela");
        }
    }


    @Transactional
    public CategoriaDTO updateCategoria(Long id, CategoriaDTO categoriaDTO) {
        try {
            Categoria categoria = repository.getReferenceById(id);
            copyToDTOCategoria(categoria, categoriaDTO);
            categoria = repository.save(categoria);
            return new CategoriaDTO(categoria);
        } catch (EntityNotFoundException ex) {
            throw new ResourceNotFoundException("Recurso não encontrado, ID:" + id);
        }
    }


    @Transactional
    public CategoriaDTO saveCategoria(CategoriaDTO categoriaDTO) {
        Categoria categoria = new Categoria();

        copyToDTOCategoria(categoria, categoriaDTO);
        categoria = repository.save(categoria);
        return new CategoriaDTO(categoria);
    }

    private void copyToDTOCategoria(Categoria categoria, CategoriaDTO categoriaDTO) {
        categoria.setNome(categoriaDTO.getNome());
    }

    @Transactional(readOnly = true)
    public List<CategoriaDTO> findAllCategorias(){
       return  repository.findAll().stream().map(CategoriaDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public CategoriaDTO findCategoriaById(Long id) {
        return new CategoriaDTO(repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado. ID: " + id)));

    }
}
