package com.github.cidarosa.ms.produto.dto;

public class ProdutoRequestDTO {

    private String nome;
    private String descricao;
    private Double valor;

    public ProdutoRequestDTO() {
    }

    public ProdutoRequestDTO(String nome, String descricao, Double valor) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
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
