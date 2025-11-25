package GamzaStudy.ToDoList.dto;

import GamzaStudy.ToDoList.entity.TodoEntity;
import lombok.Getter;

@Getter
public class TodoResponseDto {
    private long id;
    private String task;
    private boolean done;

    public TodoResponseDto(TodoEntity todo) {
        this.id = todo.getId();
        this.task = todo.getTask();
        this.done = todo.isDone();
    }
}
