package net.crudapllication.todo.controller;

import lombok.AllArgsConstructor;
import net.crudapllication.todo.dto.ToDoDto;
import net.crudapllication.todo.service.ToDoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todos")
@AllArgsConstructor
public class ToDoController {
    private ToDoService toDoService;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ToDoDto> addToDo(@RequestBody ToDoDto toDoDto){
        ToDoDto savedToDo=toDoService.addTodo(toDoDto);
        return new ResponseEntity<>(savedToDo, HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("{id}")
    public ResponseEntity<ToDoDto> getTodo(@PathVariable("id") Long todoId){
        ToDoDto toDoDto =toDoService.getTodo(todoId);
        return new ResponseEntity<>(toDoDto,HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public ResponseEntity<List<ToDoDto>> getAllTodos(){
        List<ToDoDto> todos = toDoService.getAllTodos();
        return ResponseEntity.ok(todos);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<ToDoDto> updateTodo(@RequestBody ToDoDto todoDto, @PathVariable("id") Long todoId){
        ToDoDto updatedTodo = toDoService.updateTodo(todoDto, todoId);
        return ResponseEntity.ok(updatedTodo);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long todoId){
        toDoService.deleteTodo(todoId);
        return ResponseEntity.ok("Todo deleted successfully!.");
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}/complete")
    public ResponseEntity<ToDoDto> completeTodo(@PathVariable("id") Long todoId){
        ToDoDto updatedTodo = toDoService.completeTodo(todoId);
        return ResponseEntity.ok(updatedTodo);
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}/in-complete")
    public ResponseEntity<ToDoDto> inCompleteTodo(@PathVariable("id") Long todoId){
        ToDoDto updatedTodo = toDoService.inCompleteTodo(todoId);
        return ResponseEntity.ok(updatedTodo);
    }

    }
