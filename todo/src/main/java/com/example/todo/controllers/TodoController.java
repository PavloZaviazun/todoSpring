package com.example.todo.controllers;

import com.example.todo.dao.TodoDAO;
import com.example.todo.dao.TodoListDAO;
import com.example.todo.models.Todo;
import com.example.todo.models.TodoList;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class TodoController {
    private TodoListDAO todoListDAO;
    private TodoDAO todoDAO;

    @GetMapping("/todolist/{id}/todos")
    public List <Todo> getTodos(@PathVariable int id) {
        TodoList one = todoListDAO.getOne(id);
        return one.getTodos();
    }

    @PostMapping("/todolist/{id}/todo/save")
    public void saveTodolist(@PathVariable int id,
                             @RequestParam String title,
                             @RequestParam String body,
                             @RequestParam String finalDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(finalDate, formatter);
        TodoList one = todoListDAO.getOne(id);
        List <Todo> todos = one.getTodos();
        todos.add(new Todo(title, body, localDate));
        one.setTodos(todos);
        one.setUpdatedAt(LocalDateTime.now());
        todoListDAO.save(one);
    }

    @PutMapping("/todolist/{id}/todo/{vari}/update")
    public void updateTodoList(@PathVariable int id,
                               @PathVariable int vari,
                               @RequestParam String title,
                               @RequestParam String body,
                               @RequestParam String finalDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(finalDate, formatter);
        TodoList one = todoListDAO.getOne(id);
        List <Todo> todos = one.getTodos();
        Todo el = new Todo();
        for(Todo todo : todos) {
            if(todo.getId() == vari) el = todo;
        }
        el.setTitle(title);
        el.setBody(body);
        el.setFinalDate(localDate);
        one.setTodos(todos);
        one.setUpdatedAt(LocalDateTime.now());
        todoListDAO.save(one);
    }

    @DeleteMapping("/todolist/{id}/todo/{vari}/delete")
    public void deleteTodoList(@PathVariable int id,
                               @PathVariable int vari) {
        TodoList one = todoListDAO.getOne(id);
        List <Todo> todos = one.getTodos();
        Todo el = new Todo();
        for(Todo todo : todos) {
            if(todo.getId() == vari) el = todo;
        }
        todos.remove(el);
        one.setTodos(todos);
        one.setUpdatedAt(LocalDateTime.now());
        todoListDAO.save(one);
        todoDAO.delete(el);
    }

    @PostMapping("/todolist/{id}/todos")
    public List <Todo> getTodoLists(@PathVariable int id,
                                        @RequestBody String search) {
        TodoList one = todoListDAO.getOne(id);
        return one.getTodos().stream().filter(el -> el.getTitle().toLowerCase().contains(search.toLowerCase())).collect(Collectors.toList());
    }
}
