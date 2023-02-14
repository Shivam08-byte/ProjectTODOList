package com.sheryians.s2todoapp.repositary;

import com.sheryians.s2todoapp.model.Todo;
import com.sheryians.s2todoapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository <Todo,Integer> {
    public List<Todo> findByUserOrderBySchedule(User user);
}
