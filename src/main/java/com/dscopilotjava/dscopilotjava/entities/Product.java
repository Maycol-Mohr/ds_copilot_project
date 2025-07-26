package com.dscopilotjava.dscopilotjava.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nombre;

  private int cantidad;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

  // Constructores
  public Product() {}

  public Product(String nombre, int cantidad, Category category) {

    this.nombre = nombre;

    this.cantidad = cantidad;

    this.category = category;

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

  public int getCantidad() {

    return cantidad;

  }

  public void setCantidad(int cantidad) {

    this.cantidad = cantidad;

  }

  public Category getCategory() {

    return category;

  }

  public void setCategory(Category category) {

    this.category = category;

  }

  @Override
  public String toString() {

    return "Product{" +

            "id=" + id +

            ", nombre='" + nombre + '\'' +

            ", cantidad=" + cantidad +

            ", category=" + (category != null ? category.getNombre() : "null") +

            '}';

  }

}