package br.com.tasks.tasks_api.contexts.tasks.domain.validators;

import br.com.tasks.tasks_api.contexts.tasks.domain.aggregate.Task;
import br.com.tasks.tasks_api.contexts.tasks.dto.UpdateTaskDTO;

public class FinishTask implements TaskUpdateValidationStrategy{
    @Override
    public Task validate(UpdateTaskDTO updateTaskDTO, Task task) {
        switch (task.getStatus().toString()) {
            case "PENDING" -> {
                throw new IllegalArgumentException("Tarefa ainda não iniciada");
            }
            case "DONE" -> {
                throw new IllegalArgumentException("Tarefa já finalizada");
            }
            default -> {
                return task.finish();
            }
        }
    }
}
