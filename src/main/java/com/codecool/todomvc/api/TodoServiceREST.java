package com.codecool.todomvc.api;

import com.codecool.todomvc.model.Status;
import com.codecool.todomvc.model.Todo;
import com.codecool.todomvc.repository.TodoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TodoServiceREST {

    private TodoRepository todoRepository;

    public TodoServiceREST(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @RequestMapping("/list")
    public List<Todo> getAllTodos(@Param("status") String status) {
        if (status.equals("active") || status.equals("complete")) {
            Status todoStatus = Status.valueOf(status.toUpperCase());
            return todoRepository.findByStatus(todoStatus);
        }
        return todoRepository.findAll();
    }

    @RequestMapping(value = "/addTodo")
    public String addTodo(@Param("todo-title") String title) {
        todoRepository.save(new Todo("dsfsdf"));
        return "{\"success\":true}";
    }

    @RequestMapping(value = "/todos/{id}/toggle_status", method = RequestMethod.PUT)
    public String toggleTodo(@PathVariable("id") String id) {
        Long todoId = Long.valueOf(id);
        Optional<Todo> optionalTodo = todoRepository.findById(todoId);
        Todo todo = optionalTodo.get();
        todo.setStatus(Status.COMPLETE);
        todoRepository.flush();
        return "{\"success\":true}";
    }

    @PutMapping("/todos/completed")
    public String removeCompleted() {
        todoRepository.deleteAllByStatus(Status.COMPLETE);
        return "{\"success\":true}";
    }
}
