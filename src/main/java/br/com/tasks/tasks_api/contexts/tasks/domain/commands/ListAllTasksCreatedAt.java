package br.com.tasks.tasks_api.contexts.tasks.domain.commands;

import br.com.tasks.tasks_api.contexts.tasks.domain.aggregate.Task;
import br.com.tasks.tasks_api.contexts.tasks.service.TaskService;
import br.com.tasks.tasks_api.libs.command.Command;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Getter
@Setter
public class ListAllTasksCreatedAt implements Command {
    private TaskService taskService;
    public Pageable pageable;
    public String createdAt;
    public Page<Task> tasks;

    public ListAllTasksCreatedAt(Pageable pageable, TaskService taskService, String createdAt) {
        this.taskService = taskService;
        this.pageable = pageable;
        this.createdAt = createdAt;
        this.tasks = null;
    }

    @Override
    public void execute() {
        this.tasks = taskService.findAllByCreatedAt(pageable, createdAt);
    }
}
