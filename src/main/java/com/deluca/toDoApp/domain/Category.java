package com.deluca.toDoApp.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String categoryName;

  @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
  @JsonIgnoreProperties(value = "category")
  @JsonBackReference
  private List<ToDo> toDos;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  public List<ToDo> getToDos() {
    return toDos;
  }

  public void setToDos(List<ToDo> toDos) {
    this.toDos = toDos;
  }
}
