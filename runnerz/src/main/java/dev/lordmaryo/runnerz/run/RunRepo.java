package dev.lordmaryo.runnerz.run;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
@VeryImportant
public class RunRepo {
    private List<Run> runs = new ArrayList<>();

    List<Run> findAll() {
        return runs;
    }

    @PostConstruct
    private void init() {
        runs.add(
                new Run(1, "Sunday morning run", LocalDateTime.now(),
                        LocalDateTime.now().plusHours(1), 50, Location.OUTDOOR));

        runs.add(
                new Run(2, "Tuesday morning run", LocalDateTime.now().plusDays(2),
                        LocalDateTime.now().plusHours(2), 50, Location.OUTDOOR)
        );
    }
}
