package com.example.demo;


import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class FilmeController {

  private final FilmeRepository repository;

  FilmeController(FilmeRepository repository) {
    this.repository = repository;
  }


  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/filme")
  List<Filme> all() {
    return repository.findAll();
  }
  // end::get-aggregate-root[]

  @PostMapping("/filme")
  Filme newFilme(@RequestBody Filme newFilme) {
    return repository.save(newFilme);
  }

  // Single item
  
  @GetMapping("/filme/{id}")
  Filme one(@PathVariable Long id) {
    
    return repository.findById(id)
      .orElseThrow(() -> new FilmeNotFoundException(id));
  }

  @PutMapping("/filme/{id}")
  Filme replaceFilme(@RequestBody Filme newFilme, @PathVariable Long id) {
    
    return repository.findById(id)
      .map(Filme -> {
        Filme.setNomeFilme(newFilme.getNomeFilme());
        Filme.setAno(newFilme.getAno());
        Filme.setGenero(newFilme.getGenero());
        return repository.save(Filme);
      })
      .orElseGet(() -> {
        newFilme.setId(id);
        return repository.save(newFilme);
      });
  }

  @DeleteMapping("/filme/{id}")
  void deleteFilme(@PathVariable Long id) {
    repository.deleteById(id);
  }
}