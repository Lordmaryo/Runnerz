package dev.lordmaryo.runnerz.run;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class RunJsonDataLoader implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(RunJsonDataLoader.class);

    private final RunRepo runRepository;
    private final ObjectMapper objectMapper;

    public RunJsonDataLoader(RunRepo runRepository, ObjectMapper objectMapper) {
        this.runRepository = runRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if (runRepository.count() == 0) {
            try (InputStream inputStream = this.getClass()
                    .getResourceAsStream("/data/runs.json")) {
                if (inputStream == null) {
                    throw new RuntimeException("JSON file not found");
                }

                Runs allRuns = objectMapper.readValue(inputStream, Runs.class);
                logger.info("Loading {} runs from JSON data", allRuns.runs().size());
                runRepository.saveAll(allRuns.runs());
            } catch (IOException e) {
                logger.error("Failed to read JSON data", e);
                throw new RuntimeException("Failed to read JSON data", e);
            }
        } else {
            logger.info("Skipping JSON data loading; database already contains data.");
        }
    }
}
