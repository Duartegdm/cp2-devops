package com.devops.api_cp2.controller;

import com.devops.api_cp2.model.Pessoa;
import com.devops.api_cp2.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaRepository repository;

    // CREATE
    @PostMapping
    public Pessoa criar(@RequestBody Pessoa pessoa) {
        return repository.save(pessoa);
    }

    // READ
    @GetMapping
    public List<Pessoa> listar() {
        return repository.findAll();
    }

    // UPDATE
    @PutMapping("/{id}")
    public Pessoa atualizar(@PathVariable UUID id, @RequestBody Pessoa pessoa) {
        pessoa.setId(id);
        return repository.save(pessoa);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable UUID id) {
        repository.deleteById(id);
    }
}