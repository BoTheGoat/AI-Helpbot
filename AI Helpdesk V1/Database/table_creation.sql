CREATE TABLE tickets (
    ticket_id INT AUTO_INCREMENT PRIMARY KEY,
    issue_description TEXT NOT NULL,
    category VARCHAR(50),
    priority VARCHAR(20),
    status VARCHAR(20) DEFAULT 'Open',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE ai_analysis (
    analysis_id INT AUTO_INCREMENT PRIMARY KEY,
    ticket_id INT NOT NULL,
    possible_cause TEXT,
    troubleshooting_steps TEXT,
    follow_up_question TEXT,
    FOREIGN KEY (ticket_id)
        REFERENCES tickets(ticket_id)
        ON DELETE CASCADE
);