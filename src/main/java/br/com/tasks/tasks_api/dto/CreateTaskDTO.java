package br.com.tasks.tasks_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Time;

public record CreateTaskDTO(
        @NotBlank String title,
        @NotNull Time time
) {
}
