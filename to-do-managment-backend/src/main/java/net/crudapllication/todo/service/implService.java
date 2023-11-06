package net.crudapllication.todo.service;


import lombok.AllArgsConstructor;

import net.crudapllication.todo.dto.ToDoDto;
import net.crudapllication.todo.entity.Todo;
import net.crudapllication.todo.exception.ResourceNotFoundException;
import net.crudapllication.todo.repository.ToDoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class implService implements ToDoService {

    private ToDoRepository todoRepository;

    private ModelMapper modelMapper;

    @Override
    public ToDoDto addTodo(ToDoDto todoDto) {

        Todo todo = modelMapper.map(todoDto, Todo.class);
        Todo savedTodo = todoRepository.save(todo);
        ToDoDto savedTodoDto = modelMapper.map(savedTodo, ToDoDto.class);

        return savedTodoDto;
    }

    @Override
    public ToDoDto getTodo(Long id) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id:" + id));

        return modelMapper.map(todo, ToDoDto.class);
    }

    @Override
    public List<ToDoDto> getAllTodos() {

        List<Todo> todos = todoRepository.findAll();

        return todos.stream().map((todo) -> modelMapper.map(todo, ToDoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ToDoDto updateTodo(ToDoDto todoDto, Long id) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id : " + id));
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());

        Todo updatedTodo = todoRepository.save(todo);

        return modelMapper.map(updatedTodo, ToDoDto.class);
    }

    @Override
    public void deleteTodo(Long id) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id : " + id));

        todoRepository.deleteById(id);
    }

    @Override
    public ToDoDto completeTodo(Long id) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id : " + id));

        todo.setCompleted(Boolean.TRUE);

        Todo updatedTodo = todoRepository.save(todo);

        return modelMapper.map(updatedTodo, ToDoDto.class);
    }

    @Override
    public ToDoDto inCompleteTodo(Long id) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id : " + id));

        todo.setCompleted(Boolean.FALSE);

        Todo updatedTodo = todoRepository.save(todo);

        return modelMapper.map(updatedTodo, ToDoDto.class);
    }
}