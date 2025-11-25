package GamzaStudy.ToDoList.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@NoArgsConstructor

public class TodoRequestDto {
    private String task;
    private boolean done;
}
