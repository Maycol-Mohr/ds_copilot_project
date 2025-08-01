package com.dscopilotjava.dscopilotjava.dtos;

public class CategoryDTO {

  private Long id;

  private String nombre;

  public CategoryDTO() {}

  public CategoryDTO(Long id, String nombre) {

    this.id = id;

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

  @Override
  public String toString() {

    return "CategoryDTO{" +

            "id=" + id +

            ", nombre='" + nombre + '\'' +

            '}';

  }

}
