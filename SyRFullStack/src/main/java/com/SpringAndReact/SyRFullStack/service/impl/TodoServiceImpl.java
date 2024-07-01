package com.SpringAndReact.SyRFullStack.service.impl;

import com.SpringAndReact.SyRFullStack.dto.TodoDto;
import com.SpringAndReact.SyRFullStack.entity.Todo;
import com.SpringAndReact.SyRFullStack.exception.ResourceNotFoundException;
import com.SpringAndReact.SyRFullStack.repository.TodoRepository;
import com.SpringAndReact.SyRFullStack.service.TodoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {
    private TodoRepository todoRepository;
    // Esta libreria nos permite hacer el Mapping mas limpio
    private ModelMapper modelMapper;
    @Override
    public TodoDto addTodo(TodoDto todoDto) {
      /*Convert TodoDto into Todo Jpa entity

        Todo Jpa entity
        Todo todo = new Todo();
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todo.isCompleted());
         */
        Todo todo = modelMapper.map(todoDto, Todo.class);
        // Todo Jpa entity
        Todo savedTodo = todoRepository.save(todo);
       /*convert saved Todo Jpa entity object into todoDto object

        TodoDto savedTodoDto = new TodoDto();
        savedTodoDto.setId(savedTodo.getId());
        savedTodoDto.setTitle(savedTodo.getTitle());
        savedTodoDto.setDescription(savedTodo.getDescription());
        savedTodoDto.setCompleted(savedTodo.isCompleted());
        */
        TodoDto savedTodoDto = modelMapper.map(savedTodo, TodoDto.class);
        return savedTodoDto;
    }

    @Override
    public TodoDto getTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("todo Not found with id: " + id));
        return modelMapper.map(todo, TodoDto.class);
    }

    @Override
    public void deleteTodo(Long id) {
        Todo todoDtoToDelete = todoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Todo not found with that id: "+ id));
        todoRepository.deleteById(id);
    }

    @Override
    public TodoDto completeTodo(Long id) {
        Todo todoToComplete = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found"));
        todoToComplete.setCompleted(Boolean.TRUE);
        Todo todoCompleted = todoRepository.save(todoToComplete);
        return modelMapper.map(todoCompleted, TodoDto.class);
    }

    @Override
    public TodoDto inCompleteTodo(Long id) {
        Todo todoToComplete = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found"));
        todoToComplete.setCompleted(Boolean.FALSE);
        Todo todoCompleted = todoRepository.save(todoToComplete);
        return modelMapper.map(todoCompleted, TodoDto.class);
    }

    @Override
    public List<TodoDto> getAllTodos() {
        List<Todo> todoList = todoRepository.findAll();
        return todoList.stream()
                .map((todo)-> modelMapper
                        .map(todo, TodoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TodoDto updateDto(TodoDto todoDto, Long id) {
        Todo todoToUpdate = todoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("todo not found: " + id));
        todoToUpdate.setTitle(todoDto.getTitle());
        todoToUpdate.setDescription(todoDto.getDescription());
        todoToUpdate.setCompleted(todoDto.isCompleted());
        todoRepository.save(todoToUpdate);
        return modelMapper.map(todoToUpdate, TodoDto.class);
    }

}
