package dev.lordmaryo.runnerz.run;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record Run(
        int id,
        @NotEmpty
        String title,
        LocalDateTime startedOn,
        LocalDateTime completedOn,
        @Positive
        int miles,
        Location location) {

//    public Run {
//        if (completedOn != null && startedOn != null && !completedOn.isAfter(startedOn)) {
//            throw new IllegalArgumentException("Completed on must be after started on");
//        }
//    }
}
