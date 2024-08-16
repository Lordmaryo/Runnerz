CREATE TABLE IF NOT EXISTS RUN (
    id INT NOT NULL,
    title VARCHAR(200) NOT NULL,
    started_On TIMESTAMP NOT NULL,
    completed_On TIMESTAMP,
    miles INT NOT NULL,
    version INT,
    location VARCHAR(10) NOT NULL,
    PRIMARY KEY (id)
);
