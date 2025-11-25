package GamzaStudy.ToDoList.controller;

import GamzaStudy.ToDoList.dto.LoginResponseDto;
import GamzaStudy.ToDoList.dto.UserRequestDto;
import GamzaStudy.ToDoList.dto.UserResponseDto;
import GamzaStudy.ToDoList.service.UserServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserServiceImpl userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signup(@RequestBody UserRequestDto requestDto) {
        UserResponseDto response = userService.signup(requestDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(
            @RequestBody UserRequestDto requestDto,
            HttpServletResponse response) {
        LoginResponseDto loginResponse = userService.login(requestDto, response);
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/reissue")
    public ResponseEntity<String> reissue(@CookieValue("refreshToken") String refreshToken) {
        String newAccessToken = userService.reissue(refreshToken);
        return ResponseEntity.ok(newAccessToken);
    }
}
