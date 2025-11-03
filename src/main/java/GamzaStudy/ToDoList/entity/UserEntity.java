package GamzaStudy.ToDoList.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)  // Builder 생성자만 허용           <<
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자는 JPA용으로만 사용    << 이 둘 access사용하는 이유 찾아보기
@Builder    //빌더 어케쓰는 건지 더 찾아보기
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

}