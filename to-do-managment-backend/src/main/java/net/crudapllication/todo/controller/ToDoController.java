package net.crudapllication.todo.controller;

import lombok.AllArgsConstructor;
import net.crudapllication.todo.dto.ToDoDto;
import net.crudapllication.todo.service.ToDoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/todos")
@AllArgsConstructor
public class ToDoController {
    private ToDoService toDoService;

    @PostMapping
    public ResponseEntity<ToDoDto> addToDo(@RequestBody ToDoDto toDoDto){
        ToDoDto savedToDo=toDoService.addTodo(toDoDto);

        return new ResponseEntity<>(savedToDo, HttpStatus.CREATED);
    }
}
