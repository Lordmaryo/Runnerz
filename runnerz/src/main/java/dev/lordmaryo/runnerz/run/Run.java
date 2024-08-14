package dev.lordmaryo.runnerz.run;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record Run(int id,
                  @NotEmpty
                  String title,
                  LocalDateTime startedOn,
                  LocalDateTime completedOn,
                  @Positive
                  int miles,
                  Location location) {
//    public Run {
//        if (!completedOn.isAfter(startedOn)) {
//            throw new IllegalArgumentException("Started on should be before completed on");
//        }
//    }
}
