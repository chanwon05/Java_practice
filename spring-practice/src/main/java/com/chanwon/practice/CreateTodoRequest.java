package com.chanwon.practice;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class CreateTodoRequest {
    @NotBlank(message = "할 일 제목을 입력해주세요.")
    @Size(max = 20, message = "제목은 20자 이하여야 합니다.")

    private String title;

    public CreateTodoRequest() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }
}


