package dev.lordmaryo.runnerz.run;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users/runs")
public class RunController {
    private final RunRepo runRepository;

    public RunController(RunRepo runRepository) {
        this.runRepository = runRepository;
    }

    @GetMapping("")
    public List<Run> getInfo() {
        return runRepository.findAll();
    }

    @GetMapping("/{id}")
    public Run findByid(@PathVariable int id) {
        Optional<Run> runById = runRepository.findById(id);

        if (runById.isEmpty()) {
            throw new RunNotFoundException();
        }

        return runById.get();
    }

    // Post
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@RequestBody Run run) {
        runRepository.create(run);
    }

    // update
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@RequestBody Run run, @PathVariable int id) {
        runRepository.update(run, id);
    }

    // delete
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void update(@PathVariable int id) {
        runRepository.delete(id);
    }
}
