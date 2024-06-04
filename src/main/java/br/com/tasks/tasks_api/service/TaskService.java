package br.com.tasks.tasks_api.service;

import br.com.tasks.tasks_api.domain.Task;
import br.com.tasks.tasks_api.dto.CreateTaskDTO;
import br.com.tasks.tasks_api.dto.TaskDTO;
import br.com.tasks.tasks_api.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

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
}
