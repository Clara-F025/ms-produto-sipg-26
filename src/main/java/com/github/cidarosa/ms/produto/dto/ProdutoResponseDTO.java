package com.github.cidarosa.ms.produto.dto;

import java.util.List;

public class ProdutoResponseDTO {
    private Long id;
    private String nome;
    private String descricao;
    private Double valor;

    public ProdutoResponseDTO(Long id, String nome, String descricao, Double valor) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
    }

    public static List<ProdutoResponseDTO> createMock(){
       return List.of(
               new ProdutoResponseDTO(1L, "Smart Tv", "Smart tv lg 50 polegadas", 2285.0),
               new ProdutoResponseDTO(2L, "Iphone 15 plus", "Iphone 15 plus apple", 5000.0),
               new ProdutoResponseDTO(3L, "Mouse sem fio", "Mouse sem fio Logitech", 220.0)
       );
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getValor() {
        return valor;
    }
}
