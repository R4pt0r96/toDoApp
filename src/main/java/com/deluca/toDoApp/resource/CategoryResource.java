package com.deluca.toDoApp.resource;

import com.deluca.toDoApp.domain.Category;
import com.deluca.toDoApp.repository.CategoryRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class CategoryResource {

  private final CategoryRepository categoryRepository;

  public CategoryResource(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @GetMapping("/categories")
  public ResponseEntity<List<Category>> getAll() {
    List<Category> categories = categoryRepository.findAll();
    return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
  }

  @GetMapping("/categories/{id}")
  public Category getToDo(
    @PathVariable(value = "id", required = true) Long id
  ) {
    Category category = categoryRepository.findById(id).orElse(null);
    return category;
  }

  @PostMapping("/categories")
  public ResponseEntity<Category> saveCategory(@RequestBody Category category) {
    Category savedCategory = new Category();
    try {
      savedCategory = categoryRepository.save(category);
    } catch (Exception e) {
      savedCategory = null;
      return new ResponseEntity<Category>(
        savedCategory,
        HttpStatus.BAD_REQUEST
      );
    }
    return new ResponseEntity<Category>(savedCategory, HttpStatus.OK);
  }

  @PutMapping("/categories/{id}")
  public ResponseEntity<Category> updateToDo(
    @PathVariable(value = "id", required = true) Long id,
    @RequestBody Category category
  ) {
    Category updatedCategory = new Category();
    if (!categoryRepository.findById(id).isPresent()) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    try {
      updatedCategory = categoryRepository.save(category);
    } catch (Exception e) {
      updatedCategory = null;
      return new ResponseEntity<Category>(
        updatedCategory,
        HttpStatus.BAD_REQUEST
      );
    }
    return new ResponseEntity<Category>(updatedCategory, HttpStatus.OK);
  }

  @DeleteMapping("/categories/{id}")
  public ResponseEntity<String> deleteToDo(
    @PathVariable(value = "id", required = true) Long id
  ) {
    try {
      categoryRepository.deleteById(id);
    } catch (Exception e) {
      return new ResponseEntity<String>(
        "ERRORE_DELETE_CATEGORY - " + e.getMessage(),
        HttpStatus.BAD_REQUEST
      );
    }
    return new ResponseEntity<String>("DELETE_CATEGORY_SUCCESS", HttpStatus.OK);
  }
}
