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
import java.sql.Statement;

public class AIAnalysisDAO {
   
    String url = "jdbc:mysql://localhost:3306/helpdesk_ai";
    String user = "root";
     String password = "";
     
     
     private Connection connection;

    public AIAnalysisDAO() throws SQLException{
    this.connection = DriverManager.getConnection(url, user, password);
}
    
    public void createAnalysis(int ticketID, AIAnalysis analysis) throws SQLException{
        String sql = "INSERT INTO ai_analysis (ticket_id, predicted_category, predicted_priority, recommendation, confidence_score) VALUES (?,?,?,?,?)";
        PreparedStatement state = connection.prepareStatement(sql);
        state.setInt(1, ticketID);
        state.setString(2, analysis.getCategory());
        state.setString(3, analysis.getPriority());
        state.setString(4, analysis.getRecommendation());
        state.setDouble(5, analysis.getConfidence());
        
        state.executeUpdate();
        state.close();
        
        
        
    }
    
}
    
   

