package com.chanwon.practice;

import jakarta.validation.constraints.NotNull;

public class UpdateCompletedRequest {

    @NotNull(message = "완료 여부를 입력해주세요.")
    private Boolean completed;

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}