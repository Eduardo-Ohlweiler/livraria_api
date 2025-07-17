package com.livraria_api.controller;

import com.livraria_api.dto.EmprestimoDTO;
import com.livraria_api.dto.EmprestimoUpdateDTO;
import com.livraria_api.model.Emprestimo;
import com.livraria_api.service.EmprestimoService;
import com.livraria_api.utils.MessageRest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emprestimo")
public class EmprestimoController {

    @Autowired
    private EmprestimoService service;

    @GetMapping("/{id}")
    public ResponseEntity<Emprestimo> getById(@PathVariable int id){
        Emprestimo emprestimo = this.service.getById(id);
        return new ResponseEntity<>(emprestimo, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Emprestimo>> getAll(){
        List<Emprestimo> emprestimos = this.service.getAll();
        return new ResponseEntity<>(emprestimos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Emprestimo> create(@Valid @RequestBody EmprestimoDTO dto){
        Emprestimo emprestimo = this.service.create(dto);
        return new ResponseEntity<>(emprestimo,HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Emprestimo> update(@PathVariable int id, @Valid @RequestBody EmprestimoUpdateDTO dto){
        Emprestimo emprestimo = this.service.update(id, dto);
        return new ResponseEntity<>(emprestimo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageRest> delete(@PathVariable int id){
        this.service.delete(id);
        MessageRest message = new MessageRest("Emprestimo deletado com sucesso");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PatchMapping("/{id}/devolver")
    public ResponseEntity<Emprestimo> devolver(@PathVariable int id){
        Emprestimo emprestimo = this.service.devolver(id);
        return new ResponseEntity<>(emprestimo, HttpStatus.OK);
    }
}
