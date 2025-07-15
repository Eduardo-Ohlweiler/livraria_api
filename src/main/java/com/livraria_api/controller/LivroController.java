package com.livraria_api.controller;

import com.livraria_api.dto.LivroDTO;
import com.livraria_api.model.Livro;
import com.livraria_api.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livro")
public class LivroController {

    @Autowired
    private LivroService service;

    @GetMapping
    public ResponseEntity<List<Livro>> getAll(){
        List<Livro> livros = this.service.getAll();
        return new ResponseEntity<>(livros, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> getById(@PathVariable int id){
        Livro livro = this.service.getById(id);

        return new ResponseEntity<>(livro, HttpStatus.OK);
    }

    @PostMapping
    public  ResponseEntity<Livro> create(@Valid @RequestBody LivroDTO dto){
        Livro livro = this.service.create(dto);
        return new ResponseEntity<>(livro, HttpStatus.CREATED);
    }
}
