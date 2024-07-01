package com.SpringAndReact.SyRFullStack.repository;

import com.SpringAndReact.SyRFullStack.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
