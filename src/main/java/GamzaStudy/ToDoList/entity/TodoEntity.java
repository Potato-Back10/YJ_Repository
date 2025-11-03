package GamzaStudy.ToDoList.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "todos")
@Getter
@NoArgsConstructor
public class TodoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String task;
    private boolean done;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public TodoEntity(String task, boolean done, UserEntity user) {
        this.task = task;
        this.done = done;
        this.user = user;
    }

    public void updateToDo(String task, boolean done) {
        this.task = task;
        this.done = done;
    }
    public void toggleStatus() {
        this.done = !this.done;
    }
}
