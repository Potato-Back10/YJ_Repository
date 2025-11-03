package GamzaStudy.ToDoList.service;

import GamzaStudy.ToDoList.entity.TodoEntity;
import GamzaStudy.ToDoList.entity.UserEntity;
import GamzaStudy.ToDoList.repository.TodoRepository;
import GamzaStudy.ToDoList.repository.UserRepository;
import GamzaStudy.ToDoList.dto.TodoRequestDto;
import GamzaStudy.ToDoList.dto.TodoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<TodoResponseDto> getTodos() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        return todoRepository.findByUser(user).stream()
                .map(TodoResponseDto::new)
                .collect(Collectors.toList());
    }

    public TodoResponseDto addTodo(TodoRequestDto requestDto) {

        String username = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("로그인된 사용자를 찾을 수 없습니다."));

        TodoEntity newTodo = new TodoEntity(
                requestDto.getTask(),
                requestDto.isDone(),
                user
        );

        TodoEntity savedTodo = todoRepository.save(newTodo);
        return new TodoResponseDto(savedTodo);
    }

    public TodoResponseDto updateTodo(Long id, TodoRequestDto requestDto) {
        TodoEntity todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ToDo not found with id: " + id));
        todo.updateToDo(requestDto.getTask(), requestDto.isDone());
        return new TodoResponseDto(todo);
    }

    public TodoResponseDto toggleTodoDone(Long id) {
        TodoEntity todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ToDo not found with id: " + id));
        todo.toggleStatus();
        return new TodoResponseDto(todo);
    }

    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }
}