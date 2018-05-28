/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.MessagesDaoInterface;
import dao.UserDaoInterface;
import entity.MessagesHistory;
import entity.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static service.AnalizeMessage.tokens;

/**
 *
 * @author CeachiBogdan
 */
public class DbUtil {

    public static boolean checkUserCredentials(AnalizeMessage message) {
        List<String> tokens = message.getTokens();
        String userName = tokens.get(1);
        String password = tokens.get(2);

        UserDaoInterface usersImpl = new UserImpl();
        User user = (User) usersImpl.findByEmail(userName);

        if (user != null && user.getPassword().equals(password)) {
            return true;
        }

        return false;

    }

    public static User findUserByEmail(AnalizeMessage message) {
        List<String> tokens = message.getTokens();
        String email = tokens.get(1);
        UserDaoInterface usersImpl = new UserImpl();
        User user = (User) usersImpl.findByEmail(email);

        return user;
    }

    public static boolean sendMessage(AnalizeMessage message, String senderEmail) {

        // get user by email
        User user = findUserByEmail(message); // take the user from the message package
        if (user == null) {
            return false;
        }

        // get the content from the email  
        System.out.println("message = " + message.getTokens() + " size = " + message.getTokens().size());
        
        
        String content = "";
        for (int i = 2; i <message.getTokens().size(); i++) {
            System.out.println(" i = " + i + " " + message.getTokens().get(i));
            content = content + message.getTokens().get(i) + " ";
        }
        System.out.println("content = " + content);
        System.out.println("User : " + user);

        Random random = new Random();

        MessagesHistory emailMessage = new MessagesHistory();

        emailMessage.setMessageId(random.nextInt(1000)); // nu e chiar corect ca pot exista duplicate
        emailMessage.setMessage(content);
        emailMessage.setSenderEmail(senderEmail);
        emailMessage.setUsers(user);
        //user.getMessages().add(emailMessage);
        System.out.println("emailMessage = " + emailMessage);

//       // de aici este o prostie
//         List<MessagesHistory> listOfMessages = new ArrayList<>();
//         listOfMessages.add(emailMessage);
//         user.setMessages(listOfMessages);
//         new UserImpl().insert(user);
//       
        MessagesHistoryImpl messagesImpl = new MessagesHistoryImpl();
        messagesImpl.insert(emailMessage);

        //System.out.println(emailMessage);
        return true;
    }

}
