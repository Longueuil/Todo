package com.example.spring_boot.repository;

import com.example.spring_boot.controller.entity.TodoEntity;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepo extends CrudRepository<TodoEntity, Long> {
}
