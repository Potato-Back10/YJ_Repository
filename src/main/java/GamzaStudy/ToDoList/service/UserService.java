package GamzaStudy.ToDoList.service;

import GamzaStudy.ToDoList.dto.*;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {
    UserResponseDto signup(UserRequestDto requestDto);

    LoginResponseDto login(UserRequestDto requestDto, HttpServletResponse response);

    String reissue(String refreshToken);
}