package br.com.tasks.tasks_api.contexts.tasks.domain.commands;

import br.com.tasks.tasks_api.contexts.tasks.dto.UpdateTaskDTO;
import br.com.tasks.tasks_api.contexts.tasks.service.TaskService;
import br.com.tasks.tasks_api.libs.command.Command;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class StartOrFinishTask implements Command {
    private TaskService taskService;
    public UpdateTaskDTO taskNewStatus;
    public UpdateTaskDTO taskUpdated;

    public StartOrFinishTask(UpdateTaskDTO taskNewStatus, TaskService taskService) {
        this.taskService = taskService;
        this.taskNewStatus = taskNewStatus;
        this.taskUpdated = null;
    }

    @Override
    public void execute() {
        this.taskUpdated = taskService.updateTask(taskNewStatus);
    }
}
