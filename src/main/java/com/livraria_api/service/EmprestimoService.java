package com.livraria_api.service;

import com.livraria_api.dto.EmprestimoDTO;
import com.livraria_api.dto.EmprestimoUpdateDTO;
import com.livraria_api.exception.ConflictException;
import com.livraria_api.exception.ForbbidenException;
import com.livraria_api.exception.NotFoundException;
import com.livraria_api.model.Emprestimo;
import com.livraria_api.model.Livro;
import com.livraria_api.model.Usuario;
import com.livraria_api.repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {

    @Autowired
    private LivroService livroService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EmprestimoRepository repository;

    public List<Emprestimo> getAll(){
        return this.repository.findAll();
    }

    public Emprestimo getById(int id){
        Optional<Emprestimo> emprestimo = this.repository.findById(id);
        if(emprestimo.isEmpty())
            throw new NotFoundException("Empréstimo não encontrado");

        return emprestimo.get();
    }

    public Emprestimo create(EmprestimoDTO dto) {
        Usuario usuario         = this.usuarioService.getById(dto.getUsuarioId());

        Livro livro             = this.livroService.getById(dto.getLivroId());
        if(!livro.isDisponivel())
            throw new ConflictException("Livro ja emprestado");

        Emprestimo emprestimo   = new Emprestimo();
        emprestimo.setUsuario(usuario);
        emprestimo.setLivro(livro);

        this.livroService.emprestar(livro);
        return this.repository.save(emprestimo);
    }

    public Emprestimo update(int id, EmprestimoUpdateDTO dto){
        Emprestimo emprestimo = this.getById(id);
        if(emprestimo.isDevolvido())
            throw new ForbbidenException("O emprestimo já foi devolvidoe não é possivel realizar essa ação");

        if (dto.getUsuarioId() != null){
            Usuario usuario = this.usuarioService.getById(dto.getUsuarioId());
            emprestimo.setUsuario(usuario);
        }

        if(dto.getLivroId() != null && dto.getLivroId() != emprestimo.getLivro().getId()){
            Livro livroNovo = this.livroService.getById(dto.getLivroId());
            if(!livroNovo.isDisponivel())
                throw new ConflictException("Livro ja esta emprestado");

            Livro livroAntigo = emprestimo.getLivro();

            emprestimo.setLivro(livroNovo);
            this.livroService.emprestar(livroNovo);
            this.livroService.devolver(livroAntigo);
        }

        return this.repository.save(emprestimo);
    }

    public Emprestimo devolver(int id){
        Emprestimo emprestimo = this.getById(id);
        if(emprestimo.isDevolvido())
            throw new ForbbidenException("O emprestimo já foi devolvidoe não é possivel realizar essa ação");
        emprestimo.setDevolvido(true);
        this.livroService.devolver(emprestimo.getLivro());
        return this.repository.save(emprestimo);
    }

    public void delete(int id){
        Emprestimo emprestimo = this.getById(id);
        if(emprestimo.isDevolvido())
            throw new ForbbidenException("O emprestimo já foi devolvidoe não é possivel realizar essa ação");
        this.livroService.devolver(emprestimo.getLivro());
        this.repository.delete(emprestimo);
    }
}
