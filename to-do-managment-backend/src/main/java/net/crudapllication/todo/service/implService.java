package net.crudapllication.todo.service;

import lombok.AllArgsConstructor;
import net.crudapllication.todo.dto.ToDoDto;
import net.crudapllication.todo.entity.Todo;
import net.crudapllication.todo.repository.ToDoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class implService implements ToDoService {
    private ToDoRepository toDoRepository;
    private ModelMapper modelMapper;

    @Override
    public ToDoDto addTodo(ToDoDto todoDto) {

        Todo todo = modelMapper.map(todoDto, Todo.class);
        Todo savedTodo = toDoRepository.save(todo);

        ToDoDto savedTodoDto = modelMapper.map(savedTodo, ToDoDto.class);

        return savedTodoDto;
    }
}
