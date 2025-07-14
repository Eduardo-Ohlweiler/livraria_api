package com.livraria_api.controller;

import com.livraria_api.dto.AutorDTO;
import com.livraria_api.model.Autor;
import com.livraria_api.service.AutorService;
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
}
