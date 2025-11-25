package GamzaStudy.ToDoList.service;

import GamzaStudy.ToDoList.dto.TodoRequestDto;
import GamzaStudy.ToDoList.dto.TodoResponseDto;
import GamzaStudy.ToDoList.entity.UserEntity;
import GamzaStudy.ToDoList.entity.TodoEntity;
import GamzaStudy.ToDoList.repository.TodoRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final TodoServiceUtil  todoServiceUtil;

    @Override
    @Transactional(readOnly = true)
    public List<TodoResponseDto> getTodos() {
        UserEntity user = todoServiceUtil.getCurrentUser();

        return todoRepository.findByUser(user).stream()
                .map(TodoResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public TodoResponseDto addTodo(TodoRequestDto requestDto) {
        UserEntity user = todoServiceUtil.getCurrentUser();
        TodoEntity newTodo = new TodoEntity(requestDto.getTask(), requestDto.isDone(), user);
        TodoEntity savedTodo = todoRepository.save(newTodo);
        return new TodoResponseDto(savedTodo);
    }

    @Override
    public TodoResponseDto updateTodo(Long id, TodoRequestDto requestDto) {
        String username = todoServiceUtil.getCurrentUsername();
        TodoEntity todo = todoServiceUtil.getAuthorizedTodo(id);

        todo.updateToDo(requestDto.getTask(), requestDto.isDone());
        return new TodoResponseDto(todo);
    }

    @Override
    public TodoResponseDto toggleTodoDone(Long id) {
        String username = todoServiceUtil.getCurrentUsername();
        TodoEntity todo = todoServiceUtil.getAuthorizedTodo(id);

        todo.toggleStatus();
        return new TodoResponseDto(todo);
    }

    @Override
    public void deleteTodo(Long id) {
        String username = todoServiceUtil.getCurrentUsername();
        TodoEntity todo = todoServiceUtil.getAuthorizedTodo(id);

        todoRepository.delete(todo);
    }
}