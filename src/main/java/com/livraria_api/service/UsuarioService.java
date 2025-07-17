package com.livraria_api.service;

import com.livraria_api.dto.AutorDTO;
import com.livraria_api.dto.UsuarioDTO;
import com.livraria_api.exception.NotFoundException;
import com.livraria_api.model.Autor;
import com.livraria_api.model.Usuario;
import com.livraria_api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Usuario getById(int id){
        Optional<Usuario> usuario = this.repository.findById(id);
        if(usuario.isEmpty())
            throw new NotFoundException("Usuario não encontrado");

        return usuario.get();
    }

    public Usuario update(Integer id, UsuarioDTO dto){
        Usuario usuario = this.getById(id);

        if(dto.getNome() != null)
            usuario.setNome(dto.getNome());

        return this.repository.save(usuario);
    }

    public void delete(Integer id){
        Usuario usuario = this.getById(id);
        this.repository.delete(usuario);
    }
}
