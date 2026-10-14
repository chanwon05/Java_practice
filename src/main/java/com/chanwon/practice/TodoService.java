package com.chanwon.practice;

import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TodoService {
    private final TodoJpaRepository todoJpaRepository;

    private void   validateTitle(String title) {
        if(title == null || title.isBlank()){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "할 일 제목을 입력해주세요."
            );
        }

        if(title.length() > 20){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "제목은 20자 이하여야 합니다."
            );
        }
    }

    private TodoResponse toResponse(Todo todo){
        return new TodoResponse(
            todo.getId(),
            todo.getTitle(),
            todo.isCompleted()
        );
    }

    public TodoService(TodoJpaRepository todoJpaRepository) {
        this.todoJpaRepository = todoJpaRepository;
    }

    public TodoResponse getSampleTodo() {
    return new TodoResponse(
        1L,
        "Spring 복습하기",
        false
    );
}

    public TodoResponse makePreview(String title){
        validateTitle(title);
        
        return new TodoResponse(0L, title, false);
    }

    public TodoResponse createTodo(String title) {
        validateTitle(title);

        Todo todo = new Todo(title);
        Todo saved = todoJpaRepository.save(todo);

        return toResponse(saved);
    }

    public List<TodoResponse> getTodos() {
        List<Todo> todos = todoJpaRepository.findAll();
        List<TodoResponse> responses = new ArrayList<>();

        for (Todo todo : todos) {
            responses.add(toResponse(todo));
        }

        return responses;
    }

    public TodoResponse getTodo(Long id) {
        Optional<Todo> result = todoJpaRepository.findById(id);

        if (result.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "할 일을 찾지 못했습니다."
            );
        }

        Todo todo = result.get();

        return toResponse(todo);
    }

    @Transactional
    public TodoResponse updateTitle(Long id, String title) {
        validateTitle(title);

        Todo todo = todoJpaRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "할 일을 찾지 못했습니다."
            ));

        todo.rename(title);

        return toResponse(todo);
    }

    @Transactional
    public void deleteTodo(Long id) {
        Todo todo = todoJpaRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "할 일을 찾지 못했습니다."
            ));

        todoJpaRepository.delete(todo);
    }

    public List<TodoResponse> getTodosByCompleted(boolean completed){
        List<Todo> todos = todoJpaRepository.findByCompleted(completed);
        List<TodoResponse> responses = new ArrayList<>();

        for(Todo todo : todos) {
            responses.add(toResponse(todo));
        }

        return responses;
    }

    @Transactional
    public TodoResponse updateCompleted(Long id, boolean completed) {
        Todo todo = todoJpaRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "할 일을 찾지 못했습니다."
            ));

        todo.changeCompleted(completed);

        return toResponse(todo);
    }

}