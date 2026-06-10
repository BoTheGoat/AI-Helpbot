/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aihelpdesk;

/**
 *
 * @author 1zomb
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.List;
import java.util.List;
import java.util.ArrayList;

public class TicketDAO {
    String url = "jdbc:mysql://localhost:3306/helpdesk_ai";
    String user = "root";
     String password = "";
     
     
     private Connection connection;

    public TicketDAO() throws SQLException{
    this.connection = DriverManager.getConnection(url, user, password);
}
    
    
    
 public void createTicket(Ticket ticket) throws SQLException{
    String sql = "Insert INTO tickets (issue_description, category, priority) VALUES (?,?,?)";
    PreparedStatement state = connection.prepareStatement(sql);
    state.setString(1, ticket.getIssueDescription());
    state.setString(2, ticket.getCategory());
    state.setString(3, ticket.getPriority());
    state.executeUpdate();
    
    System.out.println("Ticket successfully created!");
    
    state.close();
    
 }
 
 public List<Ticket> getAllTickets() throws SQLException{
     List<Ticket> tickets = new ArrayList();
     String sql = "SELECT * FROM tickets";
     PreparedStatement state = connection.prepareStatement(sql);
     
     ResultSet result = state.executeQuery();
     
     while(result.next()){
     int id = result.getInt("ticket_id");
     String issueDesc = result.getString("issue_description");
     String category = result.getString("category");
     String priority = result.getString("priority");
     String status = result.getString("status");
     
     Ticket ticket = new Ticket(id, issueDesc, category, priority,status);
     
     tickets.add(ticket);
     
 }
     
     result.close();
     state.close();
     
     return tickets;
     
 }
 
 public Ticket getTicketByID(int ticketID) throws SQLException{
     String sql = "SELECT * FROM tickets WHERE ticket_id = ?";
     PreparedStatement state = connection.prepareStatement(sql);
     
     state.setInt(1, ticketID);
     
     ResultSet result = state.executeQuery();
     
     Ticket ticket = null;
     if(result.next()){
         int id = result.getInt("ticket_id");
         String issueDesc = result.getString("issue_description");
         String category = result.getString("category");
         String priority = result.getString("priority");
         String status = result.getString("status");
         
         ticket = new Ticket(id,issueDesc,category,priority,status);
         
     }
     else{
         System.out.println("Ticket ID not found.");
     }
     result.close();
     state.close();
     return ticket;
     
     
     
     
 }

}