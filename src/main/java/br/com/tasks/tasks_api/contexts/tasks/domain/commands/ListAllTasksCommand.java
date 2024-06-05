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
public class ListAllTasksCommand implements Command {
    private TaskService taskService;
    public Pageable pageable;
    public Page<Task> tasks;

    public ListAllTasksCommand(Pageable pageable, TaskService taskService) {
        this.taskService = taskService;
        this.pageable = pageable;
        this.tasks = null;
    }

    @Override
    public void execute() {
        this.tasks = taskService.findAllTasks(pageable);
    }
}
