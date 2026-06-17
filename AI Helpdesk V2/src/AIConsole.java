/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aihelpdesk;

/**
 *
 * @author 1zomb
 */

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
public class AIConsole {
    Scanner userInput = new Scanner(System.in);
    int userChoice;
    String input;
   
    private TicketDAO TicketDAO;

    public AIConsole(TicketDAO TicketDAO) {
        this.TicketDAO = TicketDAO;
    }
    
    
    private int displayMenu(){
        while(true){
            System.out.println("\n========================================");
            System.out.println("          BLESSING'S HELPBOT");
            System.out.println("========================================");
            System.out.println("1. Create Ticket");
            System.out.println("2. Find ticket by ID");
            System.out.println("3. View all Tickets");
            System.out.println("4. Exit");
            
            System.out.print("Choice:");
            String input = userInput.nextLine();
            
              try{
            int choice = Integer.parseInt(input);

            if(choice >= 1 && choice <= 7){
                return choice;
            }else{
                System.out.println("Please select a option from the menu");
            }

        }catch(NumberFormatException e){
            System.out.println("Invalid input. Please enter a number.");
        }
        }
        
        
    }
    
    
    public void start() throws SQLException, IOException{
        int userChoice;
        
        do{
            userChoice = displayMenu();
            switch(userChoice){
                case 1:
                    createTicket();
                    break;
                case 2:
                    getTicketByID();
                    break;
                case 3:
                    getAllTickets();
                    break;
                    
                default:
                    System.out.println("Have a good day!");
  
            }
        } while(userChoice != 4);
    }
    
    
    
    private void createTicket()throws SQLException, IOException{
        System.out.println("\n========================================");
        System.out.println("          CREATE NEW TICKET");
        System.out.println("========================================\n");
        
        
        String issueDesc;
        boolean valid = false;
        
        
        do{
            System.out.println("Please describe the issue you are facing");
            issueDesc = userInput.nextLine();
            
            if(issueDesc.trim().isEmpty()){
                System.out.println("Please enter a valid description");
            }
            else{
                valid = true;
            }
            
        }
        while(!valid);
        
        AIAnalysisService aiService = new AIAnalysisService();
        AIAnalysis analysis = aiService.analyzeTicket(issueDesc);
        AIAnalysisDAO ai = new AIAnalysisDAO();
            
        System.out.println("\nAI Analysis");
        System.out.println("----------------------------------------");
        System.out.println("Category: " + analysis.getCategory());
        System.out.println("Priority: " + analysis.getPriority());
        System.out.println("Recommendation: " + analysis.getRecommendation());
        System.out.println("Confidence: " + analysis.getConfidence());
        System.out.println("----------------------------------------");

    Ticket ticket = new Ticket(
            issueDesc,
            analysis.getCategory(),
            analysis.getPriority()
    );

    int ticketID = TicketDAO.createTicket(ticket);
    ai.createAnalysis(ticketID, analysis);
    
         }
    
    
          private void getTicketByID() throws SQLException{
              Ticket ticket;
              boolean valid = false;
              String ticketID;
              int usersID;
              do{
                  System.out.println("Please enter your ticket ID");
                  ticketID = userInput.nextLine();
                 try{
                    usersID = Integer.parseInt(ticketID);  
                    if (usersID <= 0){
                        System.out.println("Please enter a valid ticket ID");
                    }
                    ticket = TicketDAO.getTicketByID(usersID);
                    
                    if(ticket != null){
                        System.out.println(ticket);
                        valid = true;
                    }
                    else{
                        System.out.println("Ticket not found");
                    }
                 } catch (NumberFormatException e){
                     System.out.println("Please enter a valid ID.");
                 } 
    
              } while (!valid);
          }
          
          private void getAllTickets() throws SQLException{
              List<Ticket> tickets = TicketDAO.getAllTickets();
              
               System.out.println("\n================ OPEN TICKETS ================\n");

                System.out.printf("%-5s %-40s %-15s %-10s %-10s%n",
                    "ID", "Issue", "Category", "Priority", "Status");

                System.out.println("--------------------------------------------------------------------------------");
                
                for(Ticket ticket : tickets){
                System.out.printf("%-5d %-40s %-15s %-10s %-10s%n",
                ticket.getTicketId(),
                ticket.getIssueDescription(),
                ticket.getCategory(),
                ticket.getPriority(),
                ticket.getStatus());
                }
                System.out.println();

          }
         
        
    }
    
    
    

