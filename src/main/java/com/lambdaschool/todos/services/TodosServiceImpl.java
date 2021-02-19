package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.Todos;
import com.lambdaschool.todos.models.User;
import com.lambdaschool.todos.repository.TodoRepository;
import com.lambdaschool.todos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value="todoservices")
public class TodosServiceImpl implements TodosService{
    @Autowired
    TodoRepository todorepo;
    @Autowired
    UserRepository userrepo;

    @Override
    public Todos save(Todos temptodo) {
        Todos newtodo = new Todos();
        newtodo.setDescription(temptodo.getDescription());

        if(userrepo.findById(temptodo.getUser().getUserid()).isPresent()){
            User user = userrepo.findById(temptodo.getUser().getUserid())
                    .orElseThrow(()-> new EntityNotFoundException("User with id "+temptodo.getUser().getUserid()+" could not be found"));
            newtodo.setUser(user);
        }
        return newtodo;
    }

    @Override
    public void markComplete(long todoid) {
        Todos todo = todorepo.findById(todoid)
                .orElseThrow(()->new EntityNotFoundException("Todo with id "+todoid+" not found!"));
        todo.setCompleted(true);
    }
}
