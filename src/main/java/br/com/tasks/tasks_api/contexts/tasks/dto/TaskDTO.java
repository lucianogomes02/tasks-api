package br.com.tasks.tasks_api.contexts.tasks.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

public record TaskDTO(
        @NotNull UUID id,
        @NotBlank String title,
        @NotBlank Time time,
        @NotBlank String status,
        @NotNull Date created_at,
        Date updated_at
) {

}
