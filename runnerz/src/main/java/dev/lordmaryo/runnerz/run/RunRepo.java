package dev.lordmaryo.runnerz.run;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RunRepo {
    private final List<Run> runs = new ArrayList<>();

    List<Run> findAll() {
        return runs;
    }

    Optional<Run> findById(int id) {
        return runs.stream().
                filter(run -> run.id() == id)
                .findFirst();
    }

    void create(Run run) {
        runs.add(run);
    }

    void update(Run run, int id) {
        Optional<Run> existingRun = findById(id);

        existingRun.ifPresent(value -> runs.set(runs.indexOf(value), run));
    }

    void delete(int id) {
        runs.removeIf(run -> run.id() == id);
    }

    @PostConstruct
    private void init() {
        runs.add(
                new Run(1, "Sunday morning run", LocalDateTime.now(),
                        LocalDateTime.now().plusHours(1), 20, Location.OUTDOOR));

        runs.add(
                new Run(2, "monday morning run", LocalDateTime.now().plusDays(2),
                        LocalDateTime.now().plusHours(2), 30, Location.OUTDOOR)
        );
    }
}
