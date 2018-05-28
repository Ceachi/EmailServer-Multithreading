
package persistance;

import entity.MessagesHistory;
import entity.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MessagesHistoryDao {
    
    
    
    public List<MessagesHistory> generateMessages(int count, User user) {
       
        List<MessagesHistory> listOfMessages = new ArrayList<>();
        Random random = new Random();
        
        for(int i = 0; i < count; i++) {
            MessagesHistory messages = new MessagesHistory();
            
            messages.setMessageId(random.nextInt(1000));
            messages.setSenderEmail("bogdan@gmail.com");
            messages.setMessage("Message " + i);
            messages.setUsers(user);
            
            listOfMessages.add(messages);
        }
        
        return listOfMessages;
    }
}
