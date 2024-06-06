package br.com.tasks.tasks_api.contexts.tasks.domain.validators;

import br.com.tasks.tasks_api.contexts.tasks.domain.aggregate.Task;
import br.com.tasks.tasks_api.contexts.tasks.dto.UpdateTaskDTO;

public class StartTask implements TaskUpdateValidationStrategy{
    @Override
    public Task validate(UpdateTaskDTO updateTaskDTO, Task task) {
        switch (task.getStatus().toString()) {
            case "DONE" -> {
                throw new IllegalArgumentException("Tarefa já finalizada");
            }
            case "STARTED" -> {
                throw new IllegalArgumentException("Tarefa já iniciada");
            }
            default -> {
                return task.start();
            }
        }
    }
}
