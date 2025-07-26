package com.dscopilotjava.dscopilotjava;

import com.dscopilotjava.dscopilotjava.dtos.ProductDTO;
import com.dscopilotjava.dscopilotjava.entities.Category;
import com.dscopilotjava.dscopilotjava.entities.Product;
import com.dscopilotjava.dscopilotjava.repositories.CategoryRepository;
import com.dscopilotjava.dscopilotjava.repositories.ProductRepository;
import com.dscopilotjava.dscopilotjava.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

  @InjectMocks
  private ProductService productService;

  @Mock
  private ProductRepository productRepository;

  @Mock
  private CategoryRepository categoryRepository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testGetAllProducts() {
    Category category = new Category("Electrónica");
    category.setId(1L);
    Product product = new Product("Laptop", 10, category);
    product.setId(1L);

    when(productRepository.findAll()).thenReturn(List.of(product));

    List<ProductDTO> result = productService.findAll();

    assertEquals(1, result.size());
    assertEquals("Laptop", result.get(0).getNombre());
  }

  @Test
  void testGetProductById() {
    Category category = new Category("Electrónica");
    category.setId(1L);
    Product product = new Product("Laptop", 10, category);
    product.setId(1L);

    when(productRepository.findById(1L)).thenReturn(Optional.of(product));

    ProductDTO result = productService.findById(1L);

    assertNotNull(result);
    assertEquals("Laptop", result.getNombre());
  }

  @Test
  void testCreateProduct() {
    Category category = new Category("Electrónica");
    category.setId(1L);
    ProductDTO dto = new ProductDTO(null, "Tablet", 5, 1L);
    Product product = new Product("Tablet", 5, category);
    product.setId(1L);

    when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
    when(productRepository.save(any(Product.class))).thenReturn(product);

    ProductDTO result = productService.save(dto);

    assertNotNull(result);
    assertEquals("Tablet", result.getNombre());
  }

  @Test
  void testUpdateProduct() {
    Category category = new Category("Electrónica");
    category.setId(1L);
    Product existing = new Product("Old", 1, category);
    existing.setId(1L);
    ProductDTO dto = new ProductDTO(1L, "Updated", 10, 1L);

    when(productRepository.findById(1L)).thenReturn(Optional.of(existing));
    when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
    when(productRepository.save(any(Product.class))).thenReturn(existing);

    ProductDTO result = productService.update(1L, dto);

    assertNotNull(result);
    assertEquals("Updated", result.getNombre());
  }

  @Test
  void testDeleteProduct() {
    doNothing().when(productRepository).deleteById(1L);
    productService.delete(1L);
    verify(productRepository, times(1)).deleteById(1L);
  }
}