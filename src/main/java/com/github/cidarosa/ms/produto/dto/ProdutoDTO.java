package com.github.cidarosa.ms.produto.dto;

import com.github.cidarosa.ms.produto.entities.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {
    private Long id;
    private String nome;
    private String descricao;
    private Double valor;

    public ProdutoDTO(Produto produto){
        id = produto.getId();
        nome = produto.getNome();
        descricao = produto.getDescricao();
        valor = produto.getValor();
    }

    public static List<ProdutoDTO> createMock() {
        return List.of(
                new ProdutoDTO(1L, "Smart Tv", "Smart tv lg 50 polegadas", 2285.0),
                new ProdutoDTO(2L, "Iphone 15 plus", "Iphone 15 plus apple", 5000.0),
                new ProdutoDTO(3L, "Mouse sem fio", "Mouse sem fio Logitech", 220.0)
        );
    }
}
