CREATE DATABASE helpdesk_ai;

USE helpdesk_ai;

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
    predicted_category VARCHAR(50),
    predicted_priority VARCHAR(50),
    recommendation TEXT,
    confidence_score DOUBLE,
    FOREIGN KEY (ticket_id) REFERENCES tickets(ticket_id)
);



