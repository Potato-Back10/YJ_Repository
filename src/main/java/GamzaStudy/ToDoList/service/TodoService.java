package GamzaStudy.ToDoList.service;

import GamzaStudy.ToDoList.dto.TodoRequestDto;
import GamzaStudy.ToDoList.dto.TodoResponseDto;

import java.util.List;

public interface TodoService {

    List<TodoResponseDto> getTodos();

    TodoResponseDto addTodo(TodoRequestDto requestDto);

    TodoResponseDto updateTodo(Long id, TodoRequestDto requestDto);

    TodoResponseDto toggleTodoDone(Long id);

    void deleteTodo(Long id);
}