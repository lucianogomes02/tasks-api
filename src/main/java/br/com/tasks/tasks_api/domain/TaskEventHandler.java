package br.com.tasks.tasks_api.domain;

import br.com.tasks.tasks_api.dto.UpdateTaskDTO;
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
