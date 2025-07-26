package com.dscopilotjava.dscopilotjava;

import com.dscopilotjava.dscopilotjava.controllers.CategoryController;
import com.dscopilotjava.dscopilotjava.dtos.CategoryDTO;
import com.dscopilotjava.dscopilotjava.services.CategoryService;
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

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CategoryService categoryService;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void testGetAllCategories() throws Exception {
    CategoryDTO dto = new CategoryDTO(1L, "Libros");
    Mockito.when(categoryService.findAll()).thenReturn(List.of(dto));

    mockMvc.perform(get("/api/categories"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].nombre").value("Libros"));
  }

  @Test
  void testGetCategoryById() throws Exception {
    CategoryDTO dto = new CategoryDTO(1L, "Libros");
    Mockito.when(categoryService.findById(1L)).thenReturn(dto);

    mockMvc.perform(get("/api/categories/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nombre").value("Libros"));
  }

  @Test
  void testCreateCategory() throws Exception {
    CategoryDTO dto = new CategoryDTO(null, "Libros");
    CategoryDTO saved = new CategoryDTO(1L, "Libros");

    Mockito.when(categoryService.save(any(CategoryDTO.class))).thenReturn(saved);

    mockMvc.perform(post("/api/categories")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.nombre").value("Libros"));
  }

  @Test
  void testUpdateCategory() throws Exception {
    CategoryDTO dto = new CategoryDTO(1L, "Updated");
    Mockito.when(categoryService.update(eq(1L), any(CategoryDTO.class))).thenReturn(dto);

    mockMvc.perform(put("/api/categories/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nombre").value("Updated"));
  }

  @Test
  void testDeleteCategory() throws Exception {
    mockMvc.perform(delete("/api/categories/1"))
            .andExpect(status().isOk());
  }
}