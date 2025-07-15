package com.livraria_api.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class UsuarioDTO {

    @NotBlank(message = "O nome é obrigatório")
    @Length(min = 3, max = 255, message = "O nome deve ter entre 3 e 255 caracteres")
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
