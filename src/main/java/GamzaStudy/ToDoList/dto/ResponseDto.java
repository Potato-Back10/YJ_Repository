package GamzaStudy.ToDoList.dto;

import GamzaStudy.ToDoList.entity.ToDoEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;

import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor

public class ResponseDto {
    private long id;
    private String task;
    private boolean done;

    public ResponseDto(ToDoEntity todo) {
        this.id = todo.getId();
        this.task = todo.getTask();
        this.done = todo.isDone();
    }
}
