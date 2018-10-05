package com.codecool.todomvc.controller;

import com.codecool.todomvc.model.Todo;
import com.codecool.todomvc.repository.TodoRepository;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TodoController {

    private TodoRepository todoRepository;

    public TodoController(TodoRepository service) {
        this.todoRepository = service;
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public void saveTodo(Todo todo) {
        todoRepository.save(todo);
    }

}
