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

    private static final String SUCCESS = "{\"success\":true}";
    private TodoRepository todoRepository;

    public TodoServiceREST(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public List<Todo> getAllTodos(@Param("status") String status) {
        if (status.equals("active") || status.equals("complete")) {
            Status todoStatus = Status.valueOf(status.toUpperCase());
            return todoRepository.findByStatus(todoStatus);
        }
        return todoRepository.findAll();
    }

    @RequestMapping(value = "/addTodo", method = RequestMethod.POST)
    public String addTodo(@RequestParam("todo-title") String title) {
        todoRepository.save(new Todo(title));
        return SUCCESS;
    }

    @RequestMapping(value = "/todos/{id}/toggle_status", method = RequestMethod.PUT)
    public String toggleTodo(@PathVariable("id") String id) {
        Long todoId = Long.valueOf(id);
        Optional<Todo> optionalTodo = todoRepository.findById(todoId);
        Todo todo = optionalTodo.get();
        todo.setStatus(Status.COMPLETE);
        todoRepository.flush();
        return SUCCESS;
    }

    @RequestMapping(value = "/todos/toggle_all", method = RequestMethod.PUT)
    public String toggleAll() {
        List<Todo> todos = todoRepository.findAll();
        todos.forEach(todo -> todo.setStatus(Status.COMPLETE));
        todoRepository.flush();
        return SUCCESS;
    }

    @RequestMapping(value = "/todos/completed", method = RequestMethod.DELETE)
    public String removeCompleted() {
        List<Todo> completed = todoRepository.findByStatus(Status.COMPLETE);
        todoRepository.deleteAll(completed);
        return SUCCESS;
    }
}
