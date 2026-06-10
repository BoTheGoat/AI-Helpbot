/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aihelpdesk;

/**
 *
 * @author 1zomb
 */
public class Ticket {
    private int ticketId;
    private String issueDescription;
    private String category;
    private String priority;
    private String status;

    public Ticket() {
    }

    public Ticket(String issueDescription, String category, String priority) {
        this.issueDescription = issueDescription;
        this.category = category;
        this.priority = priority;
    }

    public Ticket(int ticketId, String issueDescription, String category, String priority, String status) {
        this.ticketId = ticketId;
        this.issueDescription = issueDescription;
        this.category = category;
        this.priority = priority;
        this.status = status;
    }
    
    
    
    
    

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getIssueDescription() {
        return issueDescription;
    }

    public void setIssueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "\n=========================\n" +
           "Ticket ID: " + ticketId + "\n" +
           "Issue: " + issueDescription + "\n" +
           "Category: " + category + "\n" +
           "Priority: " + priority + "\n" +
           "Status: " + status + "\n" +
           "=========================";
    }
    
    
    
}
