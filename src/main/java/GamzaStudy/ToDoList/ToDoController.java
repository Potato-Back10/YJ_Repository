package GamzaStudy.ToDoList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ToDoController {
    @Autowired
    private ToDoRepository todoRepository;

    @PostMapping("/add")
    public ToDo addTodo(@RequestParam String task) {
        ToDo todo = new ToDo();
        todo.setTask(task);
        todo.setDone(false);
        todoRepository.save(todo);
        return todo;
    }
    @GetMapping("/todos")
    public List<ToDo> getTodos() {
        return todoRepository.findAll();
    }
}
