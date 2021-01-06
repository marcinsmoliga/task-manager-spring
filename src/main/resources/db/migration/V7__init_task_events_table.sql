DROP TABLE IF EXISTS task_events;
CREATE TABLE task_events (
    id INT AUTO_INCREMENT PRIMARY KEY,
    task_id INT,
    occurrence DATETIME,
    name VARCHAR(30)
);