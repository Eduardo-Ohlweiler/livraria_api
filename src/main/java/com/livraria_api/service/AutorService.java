package com.livraria_api.service;

import com.livraria_api.dto.AutorDTO;
import com.livraria_api.model.Autor;
import com.livraria_api.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    @Autowired
    private AutorRepository repository;

    public List<Autor> getAll(){
        try {
            return repository.findAll();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

    public Autor create(AutorDTO dto){
        try {
            Autor autor = new Autor();
            autor.setNome(dto.getNome());
            return repository.save(autor);
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }
}
