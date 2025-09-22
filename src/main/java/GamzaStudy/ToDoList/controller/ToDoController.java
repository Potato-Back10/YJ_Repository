package GamzaStudy.ToDoList.controller;

import GamzaStudy.ToDoList.dto.RequestDto;
import GamzaStudy.ToDoList.dto.ResponseDto;
import GamzaStudy.ToDoList.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todos")
public class ToDoController {

    private final ToDoService todoService;

    @GetMapping
    public ResponseEntity<List<ResponseDto>> getTodos() {
        List<ResponseDto> todos = todoService.getTodos();
        return ResponseEntity.ok(todos);
    }

    @PostMapping
    public ResponseEntity<ResponseDto> addTodo(@RequestBody RequestDto requestDto) {
        ResponseDto newTodo = todoService.addTodo(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTodo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateTodo(@PathVariable Long id, @RequestBody RequestDto requestDto) {
        ResponseDto updatedTodo = todoService.updateTodo(id, requestDto);
        return ResponseEntity.ok(updatedTodo);
    }

    @PutMapping("/{id}/toggle")
    public ResponseEntity<ResponseDto> toggleTodo(@PathVariable Long id) {
        ResponseDto toggledTodo = todoService.toggleTodoDone(id);
        return ResponseEntity.ok(toggledTodo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }
}