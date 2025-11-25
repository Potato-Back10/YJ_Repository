package GamzaStudy.ToDoList.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {       //record(final내장) //필요없는 어노테이션 제거
    private String username;
    private String password;
}
