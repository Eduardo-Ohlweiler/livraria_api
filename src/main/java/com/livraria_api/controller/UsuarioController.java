package com.livraria_api.controller;

import com.livraria_api.dto.AutorDTO;
import com.livraria_api.dto.UsuarioDTO;
import com.livraria_api.model.Autor;
import com.livraria_api.model.Usuario;
import com.livraria_api.service.UsuarioService;
import com.livraria_api.utils.MessageRest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public ResponseEntity<List<Usuario>> getAll(){
        List<Usuario> usuarios = this.service.getAll();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Usuario> create(@Valid @RequestBody UsuarioDTO dto){
        Usuario usuario = this.service.create(dto);
        return new ResponseEntity<>(usuario, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable int id, @Valid @RequestBody UsuarioDTO dto){
        Usuario usuario = this.service.update(id,dto);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageRest> delete(@PathVariable int id){
        this.service.delete(id);
        MessageRest message = new MessageRest("Usuario deletado com sucesso");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
