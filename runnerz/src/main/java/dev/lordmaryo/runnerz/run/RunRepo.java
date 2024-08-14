package dev.lordmaryo.runnerz.run;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
public class RunRepo {
    private final Logger logger = LoggerFactory.getLogger(RunRepo.class);

    private final JdbcClient jdbcClient;

    public RunRepo(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Run> findAll() {
        return jdbcClient.sql("SELECT * FROM RUN")
                .query(Run.class)
                .list();
    }

    Optional<Run> findById(int id) {
        return jdbcClient.sql("SELECT id, title, started_On, completed_On, miles," +
                        " location FROM Run WHERE id = :id")
                .param("id", id)
                .query(Run.class)
                .optional();
    }

    void create(Run run) {
        var created = jdbcClient.sql("INSERT INTO Run(id, title, started_on, completed_on, " +
                        "miles, location) VALUES(?, ?, ?, ?, ?, ?)")
                .params(List.of(run.id(), run.title(), run.startedOn(), run.completedOn(),
                        run.miles(), run.location().toString())).update();

        Assert.state(created == 1, "Failed to create run " + run.title());
    }

    void update(Run run, int id) {
        var updated = jdbcClient.sql("UPDATE Run SET title = ?, started_On = ?, completed_On = ?, " +
                        "miles = ?, location = ? WHERE id = ?")
                .params(List.of(run.id(), run.title(), run.startedOn(), run.completedOn(),
                        run.miles(), run.location().toString(), id))
                .update();

        Assert.state(updated == 1, "Failed to Update run " + run.title());
    }

    void delete(int id) {
        var deleted = jdbcClient.sql("DELETE FROM Run WHERE id = :id")
                .params("id", id)
                .update();

        Assert.state(deleted == 1, "Failed to delete run " + id);
    }

    public int count() {
        return jdbcClient.sql("SELECT * FROM Run")
                .query()
                .listOfRows()
                .size();
    }

    public List<Run> findByLocation(String location) {
        return jdbcClient.sql("SELECT * FROM Run WHERE location = :location")
                .param("location", location)
                .query(Run.class)
                .list();
    }
}
