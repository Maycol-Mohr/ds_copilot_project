package com.dscopilotjava.dscopilotjava.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "category")
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nombre;

  @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
  @JsonIgnore
  private List<Product> products;

  // Constructores
  public Category() {}

  public Category(String nombre) {

    this.nombre = nombre;

  }

  // Getters y Setters
  public Long getId() {

    return id;

  }

  public void setId(Long id) {

    this.id = id;

  }

  public String getNombre() {

    return nombre;

  }

  public void setNombre(String nombre) {

    this.nombre = nombre;

  }

  public List getProducts() {

    return products;

  }

  public void setProducts(List products) {

    this.products = products;

  }

  @Override
  public String toString() {

    return "Category{" +

            "id=" + id +

            ", nombre='" + nombre + '\'' +

            '}';

  }

}