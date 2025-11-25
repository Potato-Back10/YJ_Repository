package GamzaStudy.ToDoList.repository;

import GamzaStudy.ToDoList.entity.TodoEntity;
import GamzaStudy.ToDoList.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
    List<TodoEntity> findByUser(UserEntity user);
}