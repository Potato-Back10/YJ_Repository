package GamzaStudy.ToDoList.service;

import GamzaStudy.ToDoList.entity.UserEntity;
import GamzaStudy.ToDoList.repository.UserRepository;
import GamzaStudy.ToDoList.dto.UserRequestDto;
import GamzaStudy.ToDoList.dto.UserLoginResponseDto;
import GamzaStudy.ToDoList.dto.UserSignupResponseDto;
import GamzaStudy.ToDoList.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Value("${jwt.token.refresh-expiration-time}")
    private Long refreshExpMs;

    public UserSignupResponseDto signup(UserRequestDto requestDto) {
        if (userRepository.existsByUsername(requestDto.getUsername())) {
            throw new IllegalArgumentException("이미 존재하는 사용자명입니다.");
        }

        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());
        UserEntity user = UserEntity.builder()
                .username(requestDto.getUsername())
                .password(encodedPassword)
                .build();

        userRepository.save(user);

        return new UserSignupResponseDto(user.getId(), user.getUsername());
    }

    public UserLoginResponseDto login(UserRequestDto requestDto, HttpServletResponse response) {
        UserEntity user = userRepository.findByUsername(requestDto.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        String accessToken = jwtTokenProvider.createAccessToken(user.getId(), user.getUsername());
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getId());

        response.setHeader("Authorization", "Bearer " + accessToken);

        ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)
                .secure(false)      //개발환경이라 false 유지. 배포라면 true!
                .path("/")
                .maxAge(refreshExpMs)
                .sameSite("None")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return new UserLoginResponseDto(user.getId(), user.getUsername(), accessToken, refreshToken);
    }

    public String reissue(String refreshToken) {
        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw new IllegalArgumentException("유효하지 않은 Refresh Token입니다.");
        }

        Long userId = jwtTokenProvider.getUserId(refreshToken);
        String username = jwtTokenProvider.getUsername(refreshToken);
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        String newAccessToken = jwtTokenProvider.createAccessToken(user.getId(), username);

        return newAccessToken;
    }
}
