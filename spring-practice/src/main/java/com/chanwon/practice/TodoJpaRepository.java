package com.chanwon.practice;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoJpaRepository extends JpaRepository<Todo, Long>
{
    List<Todo> findByCompleted(boolean completed);
}