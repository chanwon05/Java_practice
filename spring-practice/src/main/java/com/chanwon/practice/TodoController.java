package com.chanwon.practice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import java.util.List;



@RestController 
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/todos/sample")
    public TodoResponse getSampleTodo(){
        return todoService.getSampleTodo();
    }

    @GetMapping("/todos/preview")
    public TodoResponse previewTodo(
        @RequestParam(name = "title") String title) {

            return todoService.makePreview(title);
        }
    
    @PostMapping("/todos")
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponse createTodo(
        @RequestBody CreateTodoRequest request) {

            return todoService.createTodo(request.getTitle());
        }
    
    @GetMapping("/todos")
    public List<TodoResponse> getTodos() {
        return todoService.getTodos();
    }
    
}
