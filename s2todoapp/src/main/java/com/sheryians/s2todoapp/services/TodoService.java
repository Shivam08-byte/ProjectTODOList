package com.sheryians.s2todoapp.services;

import com.sheryians.s2todoapp.model.Todo;
import com.sheryians.s2todoapp.model.User;
import com.sheryians.s2todoapp.repositary.TodoRepository;
import com.sheryians.s2todoapp.repositary.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("todoService")
@Transactional
public class TodoService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    TodoRepository todoRepository;

    public List<Todo> getAllTodo(){
        List <Todo> todos = null;
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> optionalUser = userRepository.findUserByEmail(email);

        if(optionalUser.isPresent()){
        todos = todoRepository.findByUserOrderBySchedule(optionalUser.get());

        return todos;
        }
        return todos;
    }

   public Todo getTodo(int id){
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        Todo todo= null;
        if(optionalTodo.isPresent()){
            todo= optionalTodo.get();
        }
        return todo;
   }

   public void saveTodo(Todo todo){
        todoRepository.save(todo);
   }

   public void deleteTodo(int id){
        todoRepository.deleteById(id);
   }
}
