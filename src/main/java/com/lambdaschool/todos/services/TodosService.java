package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.Todos;

public interface TodosService
{
    Todos save(Todos todo);
    void markComplete(long todoid);
}
