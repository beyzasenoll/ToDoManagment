package net.crudapllication.todo.repository;

import net.crudapllication.todo.entitiy.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository <Todo, Long> {
}
