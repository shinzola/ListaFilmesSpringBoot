package com.example.demo;


class FilmeNotFoundException extends RuntimeException {

  FilmeNotFoundException(Long id) {
    super("Could not find Filme " + id);
  }
}