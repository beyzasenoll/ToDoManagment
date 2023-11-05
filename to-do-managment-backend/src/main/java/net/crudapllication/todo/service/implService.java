package net.crudapllication.todo.service;

import lombok.AllArgsConstructor;
import net.crudapllication.todo.dto.ToDoDto;
import net.crudapllication.todo.entitiy.Todo;
import net.crudapllication.todo.repository.ToDoRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class implService implements ToDoService {
    private ToDoRepository toDoRepository;

    @Override
    public ToDoDto addTodo(ToDoDto toDoDto) {
        Todo todo = new Todo();
        todo.setTitle(toDoDto.getTitle());
        todo.setDescription(toDoDto.getDescription());
        todo.setCompleted(toDoDto.isCompleted());

        Todo savedTodo = toDoRepository.save(todo);

        ToDoDto savedToDto=new ToDoDto();
        savedToDto.setId(savedTodo.getId());
        savedToDto.setTitle(savedTodo.getTitle());
        savedToDto.setDescription(savedTodo.getDescription());
        savedToDto.setCompleted(savedTodo.isCompleted());
        return savedToDto;
    }
}
