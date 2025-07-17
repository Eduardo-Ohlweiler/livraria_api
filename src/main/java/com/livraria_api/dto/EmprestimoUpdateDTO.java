package com.livraria_api.dto;

import jakarta.validation.constraints.Min;

public class EmprestimoUpdateDTO {

    @Min(value = 0, message = "O usuarioId deve ser maior que zero")
    private Integer usuarioId;

    @Min(value = 0, message = "O livroId deve ser maior que zero")
    private Integer livroId;

    public EmprestimoUpdateDTO(){}

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Integer getLivroId() {
        return livroId;
    }

    public void setLivroId(Integer livroId) {
        this.livroId = livroId;
    }
}
