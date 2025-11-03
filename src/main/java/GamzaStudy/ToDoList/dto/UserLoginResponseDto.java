package GamzaStudy.ToDoList.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserLoginResponseDto {
    private Long id;
    private String username;
    private String accessToken;
    private String refreshToken;
}


