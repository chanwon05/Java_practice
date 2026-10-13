package com.chanwon.practice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import jakarta.validation.Valid;


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
        @Valid @RequestBody CreateTodoRequest request) {

        return todoService.createTodo(request.getTitle());
    }
    
    @GetMapping("/todos")
    public List<TodoResponse> getTodos(
            @RequestParam(name = "completed", required = false)
            Boolean completed) {

        if (completed == null) {
            return todoService.getTodos();
        }

        return todoService.getTodosByCompleted(completed);
    }

    @GetMapping("/todos/{id}")
    public TodoResponse getTodo(@PathVariable("id") Long id) {
        return todoService.getTodo(id);
    }

    @PatchMapping("/todos/{id}")
    public TodoResponse updateTitle(
        @PathVariable("id") Long id,
        @Valid @RequestBody CreateTodoRequest request) {

        return todoService.updateTitle(id, request.getTitle());
    }

    @DeleteMapping("/todos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable("id") Long id){
        todoService.deleteTodo(id);
    }
}
