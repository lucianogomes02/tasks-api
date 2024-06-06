package br.com.tasks.tasks_api.contexts.tasks.domain;

import br.com.tasks.tasks_api.contexts.tasks.domain.aggregate.Task;
import br.com.tasks.tasks_api.contexts.tasks.domain.validators.FinishTask;
import br.com.tasks.tasks_api.contexts.tasks.domain.validators.StartTask;
import br.com.tasks.tasks_api.contexts.tasks.domain.validators.TaskUpdateValidationStrategy;
import br.com.tasks.tasks_api.contexts.tasks.dto.UpdateTaskDTO;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class TaskUpdateEventHandler {
    private final List<TaskUpdateValidationStrategy> validators = List.of(
            new StartTask(),
            new FinishTask()
    );

    public Task handleTaskUpdated(UpdateTaskDTO updateTaskDTO, Task task) {
        for (TaskUpdateValidationStrategy validator : validators) {
            task = validator.validate(updateTaskDTO, task);
        }
        return task;
    }
}
