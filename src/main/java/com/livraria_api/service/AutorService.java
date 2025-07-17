package com.livraria_api.service;

import com.livraria_api.dto.AutorDTO;
import com.livraria_api.exception.NotFoundException;
import com.livraria_api.model.Autor;
import com.livraria_api.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Autor getById(int id){
        Optional<Autor> autor = this.repository.findById(id);
        if(autor.isEmpty())
            throw new NotFoundException("Autor não encontrado");

        return autor.get();
    }

    public Autor update(Integer id, AutorDTO dto){
        Autor autor = this.getById(id);

        if(dto.getNome() != null)
            autor.setNome(dto.getNome());

        return this.repository.save(autor);
    }

    public void delete(Integer id){
        Autor autor = this.getById(id);
        this.repository.delete(autor);
    }
}
