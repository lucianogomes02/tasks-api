package br.com.tasks.tasks_api.contexts.tasks.domain;

import br.com.tasks.tasks_api.contexts.tasks.dto.UpdateTaskDTO;
import org.springframework.stereotype.Service;

@Service
public class TaskEventHandler {
    private Task task;

    public Task handleTaskUpdated(UpdateTaskDTO updateTaskDTO, Task task) {
        switch (updateTaskDTO.status()) {
            case "DONE" -> {
                return task.finish();
            }
            case "STARTED" -> {
                return task.start();
            }
            default -> {
                return task;
            }
        }
    }
}
