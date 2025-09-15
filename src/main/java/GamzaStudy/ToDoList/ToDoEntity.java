package GamzaStudy.ToDoList;

import jakarta.persistence.*;

@Entity
public class ToDoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String task;
    private boolean done;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTask() {
        return task;
    }
    public void setTask(String task) {
        this.task = task;
    }
    public boolean isDone() {
        return done;
    }
    public void setDone(boolean done) {
        this.done = done;
    }
}
