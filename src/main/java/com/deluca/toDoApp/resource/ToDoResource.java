package com.deluca.toDoApp.resource;

import com.deluca.toDoApp.DTO.ToDoDTO;
import com.deluca.toDoApp.domain.ToDo;
import com.deluca.toDoApp.repository.ToDoRepository;
import com.deluca.toDoApp.service.ToDoService;
import java.util.List;
import org.springframework.http.HttpHeaders;
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
public class ToDoResource {

  private final ToDoRepository toDoRepository;
  private final ToDoService toDoService;

  public ToDoResource(ToDoRepository toDoRepository, ToDoService toDoService) {
    this.toDoRepository = toDoRepository;
    this.toDoService = toDoService;
  }

  @GetMapping("/todos")
  public ResponseEntity<List<ToDo>> getAll() {
    List<ToDo> todos = toDoRepository.findAll();
    // HttpHeaders headers = new HttpHeaders();
    // headers.add("TEST", "headerTEST");
    return new ResponseEntity<List<ToDo>>(todos, /*headers, */HttpStatus.OK);
  }

  @GetMapping("/todos/{id}")
  public ToDo getToDo(@PathVariable(value = "id", required = true) Long id) {
    ToDo toDo = toDoRepository.findById(id).orElse(null);
    return toDo;
  }

  @PostMapping("/todos")
  public ResponseEntity<ToDo> saveToDo(@RequestBody ToDoDTO toDoDto) {
    ToDo savedToDo = new ToDo();
    try {
      savedToDo = toDoRepository.save(toDoService.createNewToDo(toDoDto));
    } catch (Exception e) {
      savedToDo = null;
      return new ResponseEntity<ToDo>(savedToDo, HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<ToDo>(savedToDo, HttpStatus.OK);
  }

  @PutMapping("/todos/{id}")
  public ResponseEntity<ToDo> updateToDo(
    @PathVariable(value = "id", required = true) Long id,
    @RequestBody ToDo toDo
  ) {
    ToDo updatedToDo = new ToDo();
    if (!toDoRepository.findById(id).isPresent()) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    try {
      updatedToDo = toDoRepository.save(toDo);
    } catch (Exception e) {
      updatedToDo = null;
      return new ResponseEntity<ToDo>(updatedToDo, HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<ToDo>(updatedToDo, HttpStatus.OK);
  }

  @DeleteMapping("/todos/{id}")
  public ResponseEntity<String> deleteToDo(
    @PathVariable(value = "id", required = true) Long id
  ) {
    try {
      toDoRepository.deleteById(id);
    } catch (Exception e) {
      return new ResponseEntity<String>(
        "ERRORE_DELETE_TODO - " + e.getMessage(),
        HttpStatus.BAD_REQUEST
      );
    }
    return new ResponseEntity<String>("DELETE_TODO_SUCCESS", HttpStatus.OK);
  }
}
