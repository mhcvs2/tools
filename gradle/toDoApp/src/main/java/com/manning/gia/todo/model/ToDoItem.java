package com.manning.gia.todo.model;

import java.util.Objects;

public class ToDoItem implements Comparable<ToDoItem> {

    private Long id;
    private String name;
    private boolean completed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public int compareTo(ToDoItem o) {
        return this.getId().compareTo(o.getId());
    }

    @Override
    public String toString() {
        return "ToDoItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", completed=" + completed +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDoItem toDoItem = (ToDoItem) o;
        return completed == toDoItem.completed &&
                Objects.equals(id, toDoItem.id) &&
                Objects.equals(name, toDoItem.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, completed);
    }
}
