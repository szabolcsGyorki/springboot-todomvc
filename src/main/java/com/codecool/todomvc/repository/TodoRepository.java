package com.codecool.todomvc.repository;

import com.codecool.todomvc.model.Status;
import com.codecool.todomvc.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findByStatus(Status status);
}
