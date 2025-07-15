package com.livraria_api.service;

import com.livraria_api.dto.LivroDTO;
import com.livraria_api.exception.NotFoundException;
import com.livraria_api.model.Autor;
import com.livraria_api.model.Livro;
import com.livraria_api.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    @Autowired
    private AutorService autorService;

    public List<Livro> getAll(){
        return this.repository.findAll();
    }

    public Livro getById(int id){
        Optional<Livro> livro = this.repository.findById(id);
        if(livro.isEmpty())
            throw new NotFoundException("Livro não encontrado");

        return livro.get();
    }

    public Livro create(LivroDTO dto){

        Livro livro = new Livro();
        livro.setNome(dto.getNome());

        Autor autor = this.autorService.getById(dto.getAutorId());
        livro.setAutor(autor);
        livro.setAno(dto.getAno());
        return this.repository.save(livro);
    }
}
