package br.com.tasks.tasks_api.contexts.tasks.controllers;

import br.com.tasks.tasks_api.contexts.tasks.domain.aggregate.Task;
import br.com.tasks.tasks_api.contexts.tasks.domain.commands.ListAllTasksCommand;
import br.com.tasks.tasks_api.contexts.tasks.domain.commands.ScheduleTask;
import br.com.tasks.tasks_api.contexts.tasks.domain.commands.StartOrFinishTask;
import br.com.tasks.tasks_api.contexts.tasks.dto.CreateTaskDTO;
import br.com.tasks.tasks_api.contexts.tasks.dto.TaskDTO;
import br.com.tasks.tasks_api.contexts.tasks.dto.UpdateTaskDTO;
import br.com.tasks.tasks_api.contexts.tasks.service.TaskService;
import br.com.tasks.tasks_api.libs.command.CommandHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private CommandHandler commandHandler;

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<Page<Task>> findAllTasks(@PageableDefault(size = 10, sort = {"title"}) Pageable pageable) {
        ListAllTasksCommand command = new ListAllTasksCommand(pageable, taskService);
        commandHandler.handle(command);
        return ResponseEntity.ok(command.getTasks());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TaskDTO> createTask(@RequestBody @Valid CreateTaskDTO createTaskDTO, UriComponentsBuilder uriBuilder) {
        ScheduleTask command = new ScheduleTask(createTaskDTO, taskService);
        commandHandler.handle(command);
        var task = command.getTaskScheduled();
        var uri = uriBuilder.path("/tasks/{id}").buildAndExpand(task.id()).toUri();
        return ResponseEntity.created(uri).body(task);
    }

    @PatchMapping
    @Transactional
    public ResponseEntity<UpdateTaskDTO> updateTask(@RequestBody @Valid UpdateTaskDTO updatedTaskDTO) {
        StartOrFinishTask command = new StartOrFinishTask(updatedTaskDTO, taskService);
        commandHandler.handle(command);
        return ResponseEntity.ok(command.getTaskUpdated());
    }

}
