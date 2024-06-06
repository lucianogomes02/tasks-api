package br.com.tasks.tasks_api.contexts.tasks.repository;

import br.com.tasks.tasks_api.contexts.tasks.domain.aggregate.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public interface TaskRepository extends JpaRepository<Task, UUID> {
    Page<Task> findAll(Pageable pageable);
    default Page<Task> findAllByCreatedAt(Pageable pageable, String createdAt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(createdAt, formatter);

        List<Task> filteredTasks = findAll(pageable).getContent().stream()
                .filter(task -> task.getCreatedAt().toLocalDate().isEqual(localDate))
                .collect(Collectors.toList());

        return new PageImpl<>(filteredTasks, pageable, filteredTasks.size());
    }
}
