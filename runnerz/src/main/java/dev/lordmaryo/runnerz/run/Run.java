package dev.lordmaryo.runnerz.run;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.time.LocalDateTime;

public record Run(
        @Id
        int id,
        @NotEmpty
        String title,
        LocalDateTime startedOn,
        LocalDateTime completedOn,
        @Positive
        int miles,
        @Version
        Integer version,
        Location location) {
    public Run {
        if (!completedOn.isAfter(startedOn)) {
            throw new IllegalArgumentException("Started on should be before completed on");
        }
    }
}
