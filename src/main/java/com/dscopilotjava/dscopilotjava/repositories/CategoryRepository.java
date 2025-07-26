package com.dscopilotjava.dscopilotjava.repositories;

import com.dscopilotjava.dscopilotjava.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

  // Puedes agregar métodos personalizados si lo necesitas

}
