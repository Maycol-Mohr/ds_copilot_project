package com.dscopilotjava.dscopilotjava.dtos;

public class ProductDTO {

  private Long id;

  private String nombre;

  private int cantidad;

  private Long categoryId;

  public ProductDTO() {}

  public ProductDTO(Long id, String nombre, int cantidad, Long categoryId) {

    this.id = id;

    this.nombre = nombre;

    this.cantidad = cantidad;

    this.categoryId = categoryId;

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

  public Long getCategoryId() {

    return categoryId;

  }

  public void setCategoryId(Long categoryId) {

    this.categoryId = categoryId;

  }

  @Override
  public String toString() {

    return "ProductDTO{" +

            "id=" + id +

            ", nombre='" + nombre + '\'' +

            ", cantidad=" + cantidad +

            ", categoryId=" + categoryId +

            '}';

  }

}
