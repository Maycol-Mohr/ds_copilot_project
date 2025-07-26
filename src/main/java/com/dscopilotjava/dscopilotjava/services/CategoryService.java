package com.dscopilotjava.dscopilotjava.services;

import com.dscopilotjava.dscopilotjava.dtos.CategoryDTO;
import com.dscopilotjava.dscopilotjava.entities.Category;
import com.dscopilotjava.dscopilotjava.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

  @Autowired
  private CategoryRepository categoryRepository;

  public List<CategoryDTO> findAll() {
    return categoryRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
  }

  public CategoryDTO findById(Long id) {
    return categoryRepository.findById(id)
            .map(this::convertToDTO)
            .orElse(null);
  }

  public CategoryDTO save(CategoryDTO dto) {
    Category category = convertToEntity(dto);
    return convertToDTO(categoryRepository.save(category));
  }

  public CategoryDTO update(Long id, CategoryDTO dto) {
    return categoryRepository.findById(id).map(existing -> {
      existing.setNombre(dto.getNombre());
      return convertToDTO(categoryRepository.save(existing));
    }).orElse(null);
  }

  public void delete(Long id) {
    categoryRepository.deleteById(id);
  }

  private CategoryDTO convertToDTO(Category category) {
    CategoryDTO dto = new CategoryDTO();
    dto.setId(category.getId());
    dto.setNombre(category.getNombre());
    return dto;
  }

  private Category convertToEntity(CategoryDTO dto) {
    Category category = new Category();
    category.setId(dto.getId());
    category.setNombre(dto.getNombre());
    return category;
  }
}
