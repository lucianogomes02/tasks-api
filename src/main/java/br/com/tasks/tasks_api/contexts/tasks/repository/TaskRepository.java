package br.com.tasks.tasks_api.contexts.tasks.repository;

import br.com.tasks.tasks_api.contexts.tasks.domain.aggregate.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
    Page<Task> findAll(Pageable pageable);
}