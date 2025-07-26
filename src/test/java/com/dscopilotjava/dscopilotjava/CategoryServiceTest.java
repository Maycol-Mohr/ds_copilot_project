package com.dscopilotjava.dscopilotjava;

import com.dscopilotjava.dscopilotjava.dtos.CategoryDTO;
import com.dscopilotjava.dscopilotjava.entities.Category;
import com.dscopilotjava.dscopilotjava.repositories.CategoryRepository;
import com.dscopilotjava.dscopilotjava.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceTest {

  @InjectMocks
  private CategoryService categoryService;

  @Mock
  private CategoryRepository categoryRepository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testGetAllCategories() {
    Category category = new Category("Libros");
    category.setId(1L);

    when(categoryRepository.findAll()).thenReturn(List.of(category));

    List<CategoryDTO> result = categoryService.findAll();

    assertEquals(1, result.size());
    assertEquals("Libros", result.get(0).getNombre());
  }

  @Test
  void testGetCategoryById() {
    Category category = new Category("Libros");
    category.setId(1L);

    when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

    CategoryDTO result = categoryService.findById(1L);

    assertNotNull(result);
    assertEquals("Libros", result.getNombre());
  }

  @Test
  void testCreateCategory() {
    CategoryDTO dto = new CategoryDTO(null, "Ropa");
    Category category = new Category("Ropa");
    category.setId(1L);

    when(categoryRepository.save(any(Category.class))).thenReturn(category);

    CategoryDTO result = categoryService.save(dto);

    assertNotNull(result);
    assertEquals("Ropa", result.getNombre());
  }

  @Test
  void testUpdateCategory() {
    Category existing = new Category("Old");
    existing.setId(1L);
    CategoryDTO dto = new CategoryDTO(1L, "Updated");

    when(categoryRepository.findById(1L)).thenReturn(Optional.of(existing));
    when(categoryRepository.save(any(Category.class))).thenReturn(existing);

    CategoryDTO result = categoryService.update(1L, dto);

    assertNotNull(result);
    assertEquals("Updated", result.getNombre());
  }

  @Test
  void testDeleteCategory() {
    doNothing().when(categoryRepository).deleteById(1L);
    categoryService.delete(1L);
    verify(categoryRepository, times(1)).deleteById(1L);
  }
}
