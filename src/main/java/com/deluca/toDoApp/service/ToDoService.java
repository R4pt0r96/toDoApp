package com.deluca.toDoApp.service;

import com.deluca.toDoApp.DTO.ToDoDTO;
import com.deluca.toDoApp.domain.ToDo;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class ToDoService {

  public ToDo createNewToDo(ToDoDTO toDoDto) {
    ToDo newToDo = new ToDo();
    newToDo.setText(toDoDto.getText());
    newToDo.setCategory(toDoDto.getCategory());
    newToDo.setCreationDate(LocalDateTime.now());
    newToDo.setIsCompleted(false);
    return newToDo;
  }
}
