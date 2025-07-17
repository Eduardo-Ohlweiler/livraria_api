package com.livraria_api.dto;

import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;

public class LivroUpdateDTO {

    @Length(min = 3, max = 255)
    private String nome;

    @Min(value = 0, message = "O ano deve ser maior que zero")
    private Integer ano;

    @Min(value = 0, message = "O id do autor deve ser maior que zero")
    private Integer autorId;

    public LivroUpdateDTO(){}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public Integer getAutorId() {
        return autorId;
    }

    public void setAutorId(Integer autor) {
        this.autorId = autor;
    }
}
