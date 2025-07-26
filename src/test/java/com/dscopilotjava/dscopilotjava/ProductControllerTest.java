package com.dscopilotjava.dscopilotjava;

import com.dscopilotjava.dscopilotjava.controllers.ProductController;
import com.dscopilotjava.dscopilotjava.dtos.ProductDTO;
import com.dscopilotjava.dscopilotjava.services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ProductService productService;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void testGetAllProducts() throws Exception {
    ProductDTO dto = new ProductDTO(1L, "Tablet", 5, 1L);
    Mockito.when(productService.findAll()).thenReturn(List.of(dto));

    mockMvc.perform(get("/api/products"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].nombre").value("Tablet"));
  }

  @Test
  void testGetProductById() throws Exception {
    ProductDTO dto = new ProductDTO(1L, "Tablet", 5, 1L);
    Mockito.when(productService.findById(1L)).thenReturn(dto);

    mockMvc.perform(get("/api/products/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nombre").value("Tablet"));
  }

  @Test
  void testCreateProduct() throws Exception {
    ProductDTO dto = new ProductDTO(null, "Tablet", 5, 1L);
    ProductDTO saved = new ProductDTO(1L, "Tablet", 5, 1L);

    Mockito.when(productService.save(any(ProductDTO.class))).thenReturn(saved);

    mockMvc.perform(post("/api/products")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.nombre").value("Tablet"));
  }

  @Test
  void testUpdateProduct() throws Exception {
    ProductDTO dto = new ProductDTO(1L, "Updated", 10, 1L);
    Mockito.when(productService.update(eq(1L), any(ProductDTO.class))).thenReturn(dto);

    mockMvc.perform(put("/api/products/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nombre").value("Updated"));
  }

  @Test
  void testDeleteProduct() throws Exception {
    mockMvc.perform(delete("/api/products/1"))
            .andExpect(status().isOk());
  }
}