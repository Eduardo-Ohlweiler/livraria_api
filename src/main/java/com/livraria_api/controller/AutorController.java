package com.livraria_api.controller;

import com.livraria_api.dto.AutorDTO;
import com.livraria_api.dto.LivroDTO;
import com.livraria_api.model.Autor;
import com.livraria_api.service.AutorService;
import com.livraria_api.utils.MessageRest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/autor")
public class AutorController {

    @Autowired
    private AutorService service;

    @GetMapping
    public List<Autor> getAll(){
        return this.service.getAll();
    }

    @PostMapping
    public ResponseEntity<Autor> create(@Valid @RequestBody AutorDTO autorDto){
        Autor autor = this.service.create(autorDto);
        return new ResponseEntity<>(autor, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Autor> update(@PathVariable int id, @Valid @RequestBody AutorDTO dto){
        Autor autor = this.service.update(id,dto);
        return new ResponseEntity<>(autor, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageRest> delete(@PathVariable int id){
        this.service.delete(id);
        MessageRest message = new MessageRest("Autor deletado com sucesso");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
