package br.com.tasks.tasks_api.contexts.tasks.domain.aggregate;

import br.com.tasks.tasks_api.contexts.tasks.domain.value_objects.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    private Date createdAt;
    private Date updatedAt;

    public Task(String title, Time time) {
        this.title = title;
        this.time = time;
        this.status = TaskStatus.PENDING;
        this.createdAt = new Date(System.currentTimeMillis());
    }

    public Task start() {
        this.status = TaskStatus.STARTED;
        this.updatedAt = new Date(System.currentTimeMillis());
        return this;
    }

    public Task finish() {
        this.status = TaskStatus.DONE;
        this.time = new Time(0, 0, 0);
        this.updatedAt = new Date(System.currentTimeMillis());
        return this;
    }
}
