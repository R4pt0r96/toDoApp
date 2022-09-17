package com.deluca.toDoApp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ToDo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 1000)
  private String text;

  @Column(nullable = false)
  private Boolean isCompleted;

  @Column(nullable = false)
  private LocalDateTime creationDate;

  @Column(nullable = true)
  private LocalDateTime completedDate;

  @Column(nullable = true, length = 1000)
  private String extraNote;

  @ManyToOne
  @JoinColumn(name = "category_id")
  @JsonIgnoreProperties(value = "toDos")
  private Category category;

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Boolean getIsCompleted() {
    return isCompleted;
  }

  public void setIsCompleted(Boolean isCompleted) {
    this.isCompleted = isCompleted;
  }

  public LocalDateTime getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(LocalDateTime creationDate) {
    this.creationDate = creationDate;
  }

  public LocalDateTime getCompletedDate() {
    return completedDate;
  }

  public void setCompletedDate(LocalDateTime completedDate) {
    this.completedDate = completedDate;
  }

  public String getExtraNote() {
    return extraNote;
  }

  public void setExtraNote(String extraNote) {
    this.extraNote = extraNote;
  }
}
