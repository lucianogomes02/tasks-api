package br.com.tasks.tasks_api.controllers;

import br.com.tasks.tasks_api.domain.Task;
import br.com.tasks.tasks_api.dto.CreateTaskDTO;
import br.com.tasks.tasks_api.dto.TaskDTO;
import br.com.tasks.tasks_api.dto.UpdateTaskDTO;
import br.com.tasks.tasks_api.service.TaskService;
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
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<Page<Task>> findAllTasks(@PageableDefault(size = 10, sort = {"title"}) Pageable pageable) {
        var page = taskService.findAllTasks(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TaskDTO> createTask(@RequestBody @Valid CreateTaskDTO createTaskDTO, UriComponentsBuilder uriBuilder) {
        var task = taskService.createTask(createTaskDTO);
        var uri = uriBuilder.path("/tasks/{id}").buildAndExpand(task.id()).toUri();
        return ResponseEntity.created(uri).body(task);
    }

    @PatchMapping
    @Transactional
    public ResponseEntity<UpdateTaskDTO> updateTask(@RequestBody @Valid UpdateTaskDTO updatedTaskDTO) {
        var task = taskService.updateTask(updatedTaskDTO);
        return ResponseEntity.ok(task);
    }

}
