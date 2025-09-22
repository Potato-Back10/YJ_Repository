package GamzaStudy.ToDoList.repository;

import GamzaStudy.ToDoList.entity.ToDoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<ToDoEntity, Long> { }