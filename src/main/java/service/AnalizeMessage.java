package service;

import dao.UserDaoInterface;
import entity.User;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class AnalizeMessage {
    public static List<String> tokens;
    public static String MESSAGE_TYPE;
    
    
    public AnalizeMessage(String message) {
        breakMessage(message);
        MESSAGE_TYPE = tokens.get(0);
    }
    public static  void breakMessage(String message) {
        StringTokenizer st = new StringTokenizer(message);
        tokens = new ArrayList<>();
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            tokens.add(token);
        }
    }
    
    
    public  String getMessageType() { 
       return MESSAGE_TYPE;
    }
    public  List<String> getTokens() {
        return tokens;
    }

    public  void setTokens(List<String> tokens) {
        AnalizeMessage.tokens = tokens;
    }
    

}
