package com.dscopilotjava.dscopilotjava.services;

import com.dscopilotjava.dscopilotjava.dtos.ProductDTO;
import com.dscopilotjava.dscopilotjava.entities.Product;
import com.dscopilotjava.dscopilotjava.repositories.CategoryRepository;
import com.dscopilotjava.dscopilotjava.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  public List<ProductDTO> findAll() {
    return productRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
  }

  public ProductDTO findById(Long id) {
    return productRepository.findById(id)
            .map(this::convertToDTO)
            .orElse(null);
  }

  public ProductDTO save(ProductDTO dto) {
    Product product = convertToEntity(dto);
    product.setCategory(categoryRepository.findById(dto.getCategoryId()).orElse(null));
    return convertToDTO(productRepository.save(product));
  }

  public ProductDTO update(Long id, ProductDTO dto) {
    return productRepository.findById(id).map(existing -> {
      existing.setNombre(dto.getNombre());
      existing.setCantidad(dto.getCantidad());
      existing.setCategory(categoryRepository.findById(dto.getCategoryId()).orElse(null));
      return convertToDTO(productRepository.save(existing));
    }).orElse(null);
  }

  public void delete(Long id) {
    productRepository.deleteById(id);
  }

  private ProductDTO convertToDTO(Product product) {
    ProductDTO dto = new ProductDTO();
    dto.setId(product.getId());
    dto.setNombre(product.getNombre());
    dto.setCantidad(product.getCantidad());
    dto.setCategoryId(product.getCategory() != null ? product.getCategory().getId() : null);
    return dto;
  }

  private Product convertToEntity(ProductDTO dto) {
    Product product = new Product();
    product.setId(dto.getId());
    product.setNombre(dto.getNombre());
    product.setCantidad(dto.getCantidad());
    return product;
  }
}
