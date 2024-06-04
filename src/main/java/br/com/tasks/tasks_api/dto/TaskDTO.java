package br.com.tasks.tasks_api.dto;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

public record TaskDTO(
        @NotNull
        UUID id,
        String title,
        Time time,
        String status,
        Date created_at,
        Date updated_at
) {

}
