package com.livraria_api.service;

import com.livraria_api.dto.UsuarioDTO;
import com.livraria_api.model.Usuario;
import com.livraria_api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> getAll(){
        return this.repository.findAll();
    }

    public Usuario create(UsuarioDTO dto){
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        return this.repository.save(usuario);
    }
}
