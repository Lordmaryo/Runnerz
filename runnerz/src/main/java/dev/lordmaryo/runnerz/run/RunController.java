package dev.lordmaryo.runnerz.run;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users/runs")
public class RunController {
    private final RunRepository runRepository;

    public RunController(RunRepository runRepository) {
        this.runRepository = runRepository;
    }

    //Get
    @GetMapping("")
    public List<Run> getInfo() {
        return runRepository.findAll();
    }
    @GetMapping("/{id}")
    public Run findById(@PathVariable int id) {
        Optional<Run> runById = runRepository.findById(id);

        if (runById.isEmpty()) {
            throw new RunNotFoundException();
        }

        return runById.get();
    }

    // Post
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@Valid @RequestBody Run run) {
        runRepository.save(run);
    }

    // update
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@Valid @RequestBody Run run, @PathVariable int id) {
        runRepository.save(run);
    }

    // delete
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable int id) {
        runRepository.deleteById(id);
    }

    @GetMapping("/Location/{location}")
    public List<Run> findByLocation(@PathVariable String location) {
        return runRepository.findAllByLocation(location);
    }
}
