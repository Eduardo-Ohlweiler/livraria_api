package com.livraria_api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class LivroDTO {

    @NotBlank(message = "O nome é obrigatório")
    @Length(min = 3, max = 255)
    private String nome;

    @NotNull(message = "O ano é obrigatório")
    @Min(value = 0, message = "O ano deve ser maior que zero")
    private Integer ano;

    @NotNull(message = "O autor é obrigatório")
    @Min(value = 0, message = "O id do autor deve ser maior que zero")
    private Integer autorId;

    public LivroDTO(){}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getAutorId() {
        return autorId;
    }

    public void setAutorId(Integer autor) {
        this.autorId = autor;
    }
}
