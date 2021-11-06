package mensagens;

import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;

public class msn {
    
    public static void main(String args[]) throws Exception{
        String fromClient;
        String toClient;
        
        ServerSocket server = new ServerSocket(4444);
        JOptionPane.showMessageDialog(null, "Aguardando a entrada do cliente");
       
 
        boolean run = true;
        while(run){
            Socket client = server.accept();
            JOptionPane.showMessageDialog(null, "Conectado");
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);   
            
            char[] buffer = new char[1024];
            int numChars = in.read(buffer);
            
            fromClient = new String(buffer, 0, numChars);                                                                 
            
            while(fromClient != "Tchau!" && fromClient != "Cliente conectado"){
                toClient = JOptionPane.showInputDialog("Digite sua mensagem:");
                out.println(toClient);
                if(toClient.equals("Tchau!")){
                    server.close();
                    run = false;
                    break;
                }
                buffer = new char[1024];
                numChars = in.read(buffer);
                fromClient = new String(buffer, 0, numChars);
                if(fromClient.equals("Tchau!")){
                    JOptionPane.showMessageDialog(null, "Mensagem recebida:\nTchau!");
                    JOptionPane.showMessageDialog(null, "Conexão encerrada");
                    out.println(toClient);
                    client.close();
                    run = false;
                    break;
                }
                JOptionPane.showMessageDialog(null, "Mensagem recebida:\n" + fromClient);
            }
        }
        System.exit(0);  
    } 
}