package GamzaStudy.ToDoList.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "todos")
@Getter
@NoArgsConstructor
public class ToDoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String task;
    private boolean done;

    public ToDoEntity(String task, boolean done) {
        this.task = task;
        this.done = done;
    }

    public void updateToDo(String task, boolean done) {
        this.task = task;
        this.done = done;
    }
    public void toggleStatus() {
        this.done = !this.done;
    }
}
