package com.chanwon.practice;

import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }

    public TodoResponse getSampleTodo() {
        return todoRepository.findSample();
    }

    public TodoResponse makePreview(String title){
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
        
        return new TodoResponse(0L, title, false);
    }

    public TodoResponse createTodo(String title){
        if(title == null || title.isBlank()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "할 일 제목을 입력해주세요."
            );
        }

        if(title.length() > 20) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "제목은 20자 이하여야 합니다."
            );
        }

        return todoRepository.save(title);
    }

    public List<TodoResponse> getTodos() {
        return todoRepository.findAll();
    }

    public TodoResponse getTodo(Long id){
        TodoResponse todo = todoRepository.findById(id);

        if(todo == null){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "할 일을 찾지 못했습니다."
            );
        }

        return todo;
    }

}