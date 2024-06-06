package br.com.tasks.tasks_api.contexts.tasks.service;

import br.com.tasks.tasks_api.contexts.tasks.domain.aggregate.Task;
import br.com.tasks.tasks_api.contexts.tasks.domain.validators.TaskUpdateEventHandler;
import br.com.tasks.tasks_api.contexts.tasks.dto.CreateTaskDTO;
import br.com.tasks.tasks_api.contexts.tasks.dto.TaskDTO;
import br.com.tasks.tasks_api.contexts.tasks.dto.UpdateTaskDTO;
import br.com.tasks.tasks_api.contexts.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskUpdateEventHandler taskEventHandler;

    public TaskDTO createTask(CreateTaskDTO createTaskDTO) {
        Task task = new Task(createTaskDTO.title(), createTaskDTO.time());
        taskRepository.save(task);
        return new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.getTime(),
                task.getStatus().toString(),
                task.getCreatedAt(),
                task.getUpdatedAt()
        );
    }

    public Page<Task> findAllTasks(@PageableDefault(size = 10, sort = {"title"}) Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    public UpdateTaskDTO updateTask(UpdateTaskDTO updateTaskDTO) {
        var task = taskRepository.findById(updateTaskDTO.id());

        var taskUpdated = taskEventHandler.handleTaskUpdated(updateTaskDTO, task.get());
        taskRepository.save(taskUpdated);

        return new UpdateTaskDTO(taskUpdated.getId(), taskUpdated.getStatus().toString());
    }
}
