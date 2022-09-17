package com.deluca.toDoApp.repository;

import com.deluca.toDoApp.domain.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {}
