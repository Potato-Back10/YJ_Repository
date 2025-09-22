package GamzaStudy.ToDoList.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class RequestDto {
    private String task;
    private boolean done;
}
