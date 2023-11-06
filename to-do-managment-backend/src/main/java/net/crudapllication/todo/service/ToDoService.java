package net.crudapllication.todo.service;

import net.crudapllication.todo.dto.ToDoDto;

import java.util.List;

public interface ToDoService {
    ToDoDto addTodo(ToDoDto toDoDto);
    ToDoDto getTodo(Long id);

    List<ToDoDto> getAllTodos();

    ToDoDto updateTodo(ToDoDto todoDto, Long id);

    void deleteTodo(Long id);

    ToDoDto completeTodo(Long id);

    ToDoDto inCompleteTodo(Long id);
}
