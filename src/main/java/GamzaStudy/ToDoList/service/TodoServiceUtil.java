package GamzaStudy.ToDoList.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import GamzaStudy.ToDoList.entity.UserEntity;
import GamzaStudy.ToDoList.entity.TodoEntity;
import GamzaStudy.ToDoList.repository.TodoRepository;
import GamzaStudy.ToDoList.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class TodoServiceUtil {
    private final UserRepository userRepository;
    private final TodoRepository todoRepository;

    public String getCurrentUsername() {
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
    }

    public UserEntity getCurrentUser() {
        String username = getCurrentUsername();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("로그인된 사용자를 찾을 수 없습니다."));
    }

    public TodoEntity getAuthorizedTodo(Long id) {
        String username = getCurrentUsername();
        TodoEntity todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ToDo를 찾을 수 없습니다. id=" + id));

        if (!todo.getUser().getUsername().equals(username)) {
            throw new SecurityException("Todo 접근 권한이 없습니다.");
        }
        return todo;
    }
}
