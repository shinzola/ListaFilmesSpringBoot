package com.example.demo;

import java.util.Objects;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;

 import jakarta.persistence.*; // for Spring Boot 3

@Entity
class Filme {

  private @Id @GeneratedValue Long id;
  private String nome_filme;
  private int ano;
  private String genero;

  Filme() {}

  Filme(String nome_filme, int ano, String genero) {

    this.nome_filme = nome_filme;
    this.ano = ano;
    this.genero = genero;


  }

  public Long getId() {
    return this.id;
  }

  public String getNomeFilme() {
    return this.nome_filme;
  }

    public int getAno() {
    return this.ano;
  }

  public String getGenero() {
    return this.genero;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setNomeFilme(String nome_filme) {
    this.nome_filme = nome_filme;
  }

  public void setAno(int ano) {
    this.ano = ano;
  }

    public void setGenero(String genero) {
    this.genero = genero;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o)
      return true;
    if (!(o instanceof Filme ))
      return false;
    Filme filme = (Filme) o;
    return Objects.equals(this.id, filme.id) && Objects.equals(this.nome_filme, filme.nome_filme)
        && Objects.equals(this.ano, filme.ano) && Objects.equals(this.genero, filme.genero);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.nome_filme, this.ano, this.genero);
  }

  @Override
      public String toString() {
          return "filme{" + 
                "id=" + this.id + 
                ", Filme='" + this.nome_filme + '\'' + 
                ", ano='" + this.ano + '\'' + 
                ", genero='" + this.genero + '\'' + 
                '}';
      }
}