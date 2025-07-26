package com.dscopilotjava.dscopilotjava.controllers;

import com.dscopilotjava.dscopilotjava.dtos.CategoryDTO;
import com.dscopilotjava.dscopilotjava.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @GetMapping
  public List getAllCategories() {

    return categoryService.findAll();

  }

  @GetMapping("/{id}")
  public CategoryDTO getCategoryById(@PathVariable Long id) {

    return categoryService.findById(id);

  }

  @PostMapping
  public CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO) {

    return categoryService.save(categoryDTO);

  }

  @PutMapping("/{id}")
  public CategoryDTO updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {

    return categoryService.update(id, categoryDTO);

  }

  @DeleteMapping("/{id}")
  public void deleteCategory(@PathVariable Long id) {

    categoryService.delete(id);

  }

}

