package com.dscopilotjava.dscopilotjava.repositories;

import com.dscopilotjava.dscopilotjava.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  // Puedes agregar métodos personalizados si lo necesitas

}


