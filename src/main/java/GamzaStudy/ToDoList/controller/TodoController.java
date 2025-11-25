package GamzaStudy.ToDoList.controller;

import GamzaStudy.ToDoList.dto.TodoResponseDto;
import GamzaStudy.ToDoList.dto.TodoRequestDto;
import GamzaStudy.ToDoList.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public ResponseEntity<List<TodoResponseDto>> getTodos() {
        List<TodoResponseDto> todos = todoService.getTodos();
        return ResponseEntity.ok(todos);
    }

    @PostMapping
    public ResponseEntity<TodoResponseDto> addTodo(@RequestBody TodoRequestDto todoRequestDto) {
        TodoResponseDto newTodo = todoService.addTodo(todoRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTodo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoResponseDto> updateTodo(@PathVariable Long id, @RequestBody TodoRequestDto todoRequestDto) {
        TodoResponseDto updatedTodo = todoService.updateTodo(id, todoRequestDto);
        return ResponseEntity.ok(updatedTodo);
    }

    @PutMapping("/{id}/toggle")
    public ResponseEntity<TodoResponseDto> toggleTodo(@PathVariable Long id) {
        TodoResponseDto toggledTodo = todoService.toggleTodoDone(id);
        return ResponseEntity.ok(toggledTodo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }
}