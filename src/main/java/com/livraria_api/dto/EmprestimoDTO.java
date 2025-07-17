package com.livraria_api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class EmprestimoDTO {

    @NotNull(message = "O usuarioId é obrigatorio")
    @Min(value = 0, message = "O usuarioId deve ser maior que zero")
    private Integer usuarioId;

    @NotNull(message = "O livroId é obrigatorio")
    @Min(value = 0, message = "O livroId deve ser maior que zero")
    private Integer livroId;

    public EmprestimoDTO(){}

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
