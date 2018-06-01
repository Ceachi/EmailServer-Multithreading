/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import dao.UserDaoInterface;
import entity.MessagesHistory;
import entity.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import service.UserImpl;
import service.AnalizeMessage;
import service.DbUtil;

/**
 *
 * @author CeachiBogdan
 */
public class ServerThread extends Thread {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ServerThread(Socket socket) throws Exception {
        this.socket = socket;
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    }

    @Override
    public void run() {
        final int CLIENT_EMAIL = 1;
        String clientConnectedEmail = null;
        try {
            while (true) {
                


                // se asteapta primirea unui mesaj
                String message = receiveMessage();

                System.out.println("am primit mesajul de la client : " + message);
                //TO DO: VERIFICA TIPUL MESAJULUI
                AnalizeMessage analizedMessage = new AnalizeMessage(message);
                String messageType = analizedMessage.getMessageType();

                if (messageType.equals("LOGIN") && DbUtil.checkUserCredentials(analizedMessage) == true) {
                    clientConnectedEmail = analizedMessage.getTokens().get(CLIENT_EMAIL);
                    sendMessage("LOGIN SUCCESSFULL");
                } else if (messageType.equals("SEND")) {
                    // verific daca exista mail
                    System.out.println("este un mesaj de SEND");
                    boolean check = DbUtil.sendMessage(analizedMessage, clientConnectedEmail);
//                System.out.println("ajungem aici");
//                System.out.println("check = " + check);
//                if(check == false) {
//                    sendMessage("EMAIL DOESN'T EXIST"); 
//                } else {
//                    sendMessage("EMAIL SUCESSFULLY SEND");
//                    System.out.println("Am trimis email de successfull");
//                }
                } else if (messageType.equals("GET")) {
                    System.out.println("Userul: " + clientConnectedEmail + " a trimis un mesaj de "
                            + analizedMessage.getTokens().get(1));

                    UserDaoInterface usersImpl = new UserImpl();
                    User user = (User) usersImpl.findByEmail(clientConnectedEmail);
                    
                    System.out.printf("\n\n Lista mesajelor este :\n\n ");
                    
                    
                    String historyMessages = "INBOX";
                    
                    
                    for (MessagesHistory userMessage : user.getMessages()) {
                        historyMessages = historyMessages + " " + userMessage.getSenderEmail() + " " + userMessage.getMessage() + " ";
                    }
                    
                    //System.out.println(historyMessages);
                    sendMessage(historyMessages);
                    
                    

                }

                // DACA INREGISTRARE ATUNCI TRIMTE INSEREAZA IN BAZA DE DATE
                //trimit catre toti
                // ClientsContainer.sendMessageToAll(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void sendMessage(String message) {
        out.println(message);
    }

    public String receiveMessage() throws IOException {
        return in.readLine();

    }

}
