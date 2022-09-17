package com.deluca.toDoApp.DTO;

import com.deluca.toDoApp.domain.Category;

public class ToDoDTO {

  private String text;

  private Category category;

  public ToDoDTO(String text, Category category) {
    this.text = text;
    this.category = category;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }
}
