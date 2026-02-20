package com.github.cidarosa.ms.produto.repositories;

import com.github.cidarosa.ms.produto.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
