package com.codecool.todomvc.service;

import com.codecool.todomvc.model.Todo;
import com.codecool.todomvc.repository.TodoRepository;
import org.springframework.stereotype.Component;

@Component
public class InitializerBean {

    public InitializerBean(TodoRepository todoRepository) {
        todoRepository.save(new Todo("first TODO item"));
        todoRepository.save(new Todo("second TODO item"));
        todoRepository.save(new Todo("third TODO item"));
    }
}
