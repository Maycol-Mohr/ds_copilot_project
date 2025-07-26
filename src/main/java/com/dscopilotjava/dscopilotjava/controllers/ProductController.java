package com.dscopilotjava.dscopilotjava.controllers;

import com.dscopilotjava.dscopilotjava.dtos.ProductDTO;
import com.dscopilotjava.dscopilotjava.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping
  public List getAllProducts() {

    return productService.findAll();

  }

  @GetMapping("/{id}")
  public ProductDTO getProductById(@PathVariable Long id) {

    return productService.findById(id);

  }

  @PostMapping
  public ProductDTO createProduct(@RequestBody ProductDTO productDTO) {

    return productService.save(productDTO);

  }

  @PutMapping("/{id}")
  public ProductDTO updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {

    return productService.update(id, productDTO);

  }

  @DeleteMapping("/{id}")
  public void deleteProduct(@PathVariable Long id) {

    productService.delete(id);

  }

}
