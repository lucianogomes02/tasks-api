package br.com.tasks.tasks_api.domain;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

@Table(name = "tasks")
@Entity(name = "Task")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Task {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private Time time;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;


    private Date created_at;
    private Date updated_at;

    public Task(String title, Time time) {
        this.title = title;
        this.time = time;
        this.status = TaskStatus.PENDING;
    }

    public void start() {
        this.status = TaskStatus.STARTED;
    }
}
