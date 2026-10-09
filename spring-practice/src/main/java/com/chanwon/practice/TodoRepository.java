package com.chanwon.practice;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;


@Repository 
public class TodoRepository {
    
    public TodoResponse findSample() {
        return new TodoResponse(
            1L,
        "Spring 복습하기",
    false
            );
    }

    private final List<TodoResponse> todos = new ArrayList<>();
    private long nextId = 1;

    public TodoResponse save(String title){
        TodoResponse todo = new TodoResponse(nextId, title, false);

        todos.add(todo);
        nextId++;

        return todo;
    }

    public List<TodoResponse> findAll() {
        return new ArrayList<>(todos);
    }

    public TodoResponse findById(Long id){
        for(TodoResponse todo : todos) {
            if(todo.getId().equals(id)){
                return todo;
            }
        }

        return null;
    }
}
