package br.com.tasks.tasks_api.contexts.tasks.domain.commands;

import br.com.tasks.tasks_api.contexts.tasks.dto.CreateTaskDTO;
import br.com.tasks.tasks_api.contexts.tasks.dto.TaskDTO;
import br.com.tasks.tasks_api.contexts.tasks.service.TaskService;
import br.com.tasks.tasks_api.libs.command.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleTask implements Command {
    private TaskService taskService;
    private CreateTaskDTO createTaskDTO;
    public TaskDTO taskScheduled;

    public ScheduleTask(CreateTaskDTO createTaskDTO, TaskService taskService) {
        this.taskService = taskService;
        this.createTaskDTO = createTaskDTO;
        this.taskScheduled = null;
    }

    @Override
    public void execute() {
        this.taskScheduled = taskService.createTask(createTaskDTO);
    }
}
