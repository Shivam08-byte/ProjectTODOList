package com.sheryians.s2todoapp.controllers;

import com.sheryians.s2todoapp.model.Todo;
import com.sheryians.s2todoapp.model.User;
import com.sheryians.s2todoapp.repositary.UserRepository;
import com.sheryians.s2todoapp.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
public class TodoController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    TodoService todoService;

    @GetMapping("/todo/add")
    public String addTodoGet(Model model, Principal principal){
        Optional<User> optionalUser= userRepository.findUserByEmail(principal.getName());
        if(optionalUser.isPresent()){
            Todo todo=new Todo();
            todo.setUser(optionalUser.get());
            model.addAttribute("todo",todo);
            return "addTodo";
        }else {
            return "/error";
        }

    }
    @PostMapping("/todo/add")
    public String addTodo(Todo todo){
     todoService.saveTodo(todo);
     return "redirect:/welcome";
    }

    @GetMapping("/todo/delete/{id}")
    public String deleteTodo(@PathVariable int id){
        todoService.deleteTodo(id);
        return "redirect:/welcome";
    }

    @GetMapping("/todo/update/{id}")
    public String updateTodo(@PathVariable int id,Model model){
        if(todoService.getTodo(id)!=null){
            model.addAttribute("todo",todoService.getTodo(id));
            return "addTodo";
        }
        else{
            return "/error";
        }
    }
}
