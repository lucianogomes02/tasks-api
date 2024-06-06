package br.com.tasks.tasks_api.contexts.tasks.domain.validators;

import br.com.tasks.tasks_api.contexts.tasks.domain.aggregate.Task;
import br.com.tasks.tasks_api.contexts.tasks.dto.UpdateTaskDTO;

public interface TaskUpdateValidationStrategy {
    Task validate(UpdateTaskDTO updateTaskDTO, Task task);
}
