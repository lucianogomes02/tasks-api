package br.com.tasks.tasks_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UpdateTaskDTO(
        @NotNull UUID id,
        @NotBlank String status
) {
}
