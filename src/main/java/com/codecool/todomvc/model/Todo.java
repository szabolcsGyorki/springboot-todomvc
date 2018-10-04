package com.codecool.todomvc.model;

import javax.persistence.*;

@Entity
public class Todo {

    @Id
    @GeneratedValue
    private long id;
    private String title;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Todo(String title) {
        this.title = title;
        this.status = Status.ACTIVE;
    }

    public Todo() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isComplete() {
        return this.status == Status.COMPLETE;
    }
}
