package GamzaStudy.ToDoList.controller;

import GamzaStudy.ToDoList.dto.UserLoginResponseDto;
import GamzaStudy.ToDoList.dto.UserRequestDto;
import GamzaStudy.ToDoList.dto.UserSignupResponseDto;
import GamzaStudy.ToDoList.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserSignupResponseDto> signup(@RequestBody UserRequestDto requestDto) {
        UserSignupResponseDto response = userService.signup(requestDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(
            @RequestBody UserRequestDto requestDto,
            HttpServletResponse response) {
        UserLoginResponseDto loginResponse = userService.login(requestDto, response);
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/reissue")
    public ResponseEntity<String> reissue(@CookieValue("refreshToken") String refreshToken) {
        String newAccessToken = userService.reissue(refreshToken);
        return ResponseEntity.ok(newAccessToken);
    }
}
