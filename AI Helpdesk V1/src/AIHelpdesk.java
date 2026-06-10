/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package aihelpdesk;

import java.sql.SQLException;

/**
 *
 * @author 1zomb
 */
public class AIHelpdesk {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        TicketDAO ticketsDAO = new TicketDAO();
        
        AIConsole helpdesk = new AIConsole(ticketsDAO);
        
        helpdesk.start();
    
   
   
    
}
}