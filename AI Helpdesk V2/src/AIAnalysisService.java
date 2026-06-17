/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aihelpdesk;

/**
 *
 * @author 1zomb
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.IOException;
import java.util.stream.Collectors;
import java.lang.ProcessBuilder;
import java.lang.Process;
import com.google.gson.Gson;

public class AIAnalysisService {
    private String pythonPath;
    private String scriptPath;
    
    public AIAnalysis analyzeTicket(String issueDesc) throws IOException{
        try{
           pythonPath = "C:\\Users\\1zomb\\PycharmProjects\\AI_Helpbot\\.venv\\Scripts\\python.exe";
           scriptPath = "C:\\Users\\1zomb\\PycharmProjects\\AI_Helpbot\\ticket_analyzer.py";
           
           ProcessBuilder pb = new ProcessBuilder(pythonPath,scriptPath,issueDesc);
           System.out.println("Calling python.....");
           Process process = pb.start();
           
           BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
           String jsonOutput = "";
           String line;
           
           while((line = reader.readLine()) != null){
               jsonOutput+=line;
           }
           Gson gson = new Gson();
            
           AIAnalysis analysis = gson.fromJson(jsonOutput, AIAnalysis.class);
           
          return analysis;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        
     
        
    }
    
}
