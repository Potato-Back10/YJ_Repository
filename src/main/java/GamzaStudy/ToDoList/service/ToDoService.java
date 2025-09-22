package GamzaStudy.ToDoList.service;

import GamzaStudy.ToDoList.repository.ToDoRepository;
import GamzaStudy.ToDoList.dto.RequestDto;
import GamzaStudy.ToDoList.dto.ResponseDto;
import GamzaStudy.ToDoList.entity.ToDoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ToDoService {

    private final ToDoRepository todoRepository;

    @Transactional(readOnly = true)
    public List<ResponseDto> getTodos() {
        return todoRepository.findAll().stream()
                .map(ResponseDto::new)
                .collect(Collectors.toList());
    }

    public ResponseDto addTodo(RequestDto requestDto) {
        ToDoEntity newTodo = new ToDoEntity(requestDto.getTask(), requestDto.isDone());
        ToDoEntity savedTodo = todoRepository.save(newTodo);
        return new ResponseDto(savedTodo);
    }

    public ResponseDto updateTodo(Long id, RequestDto requestDto) {
        ToDoEntity todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ToDo not found with id: " + id));
        todo.updateToDo(requestDto.getTask(), requestDto.isDone());
        return new ResponseDto(todo);
    }

    public ResponseDto toggleTodoDone(Long id) {
        ToDoEntity todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ToDo not found with id: " + id));
        todo.toggleStatus();
        return new ResponseDto(todo);
    }

    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }
}