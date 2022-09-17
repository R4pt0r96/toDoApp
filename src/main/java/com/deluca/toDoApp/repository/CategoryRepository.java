package com.deluca.toDoApp.repository;

import com.deluca.toDoApp.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {}
