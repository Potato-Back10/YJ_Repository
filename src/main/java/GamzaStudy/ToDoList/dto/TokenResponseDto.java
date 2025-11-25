package GamzaStudy.ToDoList.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenResponseDto {
    private String accessToken;
    private String refreshToken;        //그냥 token하나 만드는거랑 두개 나누는거랑 무슨차이임? : 해결함
}
