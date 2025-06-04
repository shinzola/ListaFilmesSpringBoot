package com.example.demo;


import org.springframework.data.jpa.repository.JpaRepository;

interface FilmeRepository extends JpaRepository<Filme, Long> {

}