package GamzaStudy.ToDoList.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class UserEntity implements UserDetails{     //userDetails 구현을 빼고

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ToDoEntity> todos = new ArrayList<>();

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // 여기 부분은 따로 빼서 엔티티는 순수 데이터 매핑으로만...! **수정하기
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER")); // 기본 권한 'ROLE_USER' 부여
    }

    @Override
    public boolean isAccountNonExpired() {  // 계정 만료 여부
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {   // 계정 잠금 여부
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {  // 비밀번호 만료 여부
        return true;
    }

    @Override
    public boolean isEnabled() {    // 계정 활성화 여부
        return true;
    }
}