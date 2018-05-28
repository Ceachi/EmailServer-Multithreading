
package persistance;

import entity.User;
import java.util.ArrayList;
import java.util.List;


public class UserDao {
    
    public List<User> getAllAvailableUsers() {
        
        
        List<User> listOfUsers = new ArrayList<>();
        
        for(int i = 0; i < 10; i++) {
            User user = new User();
            user.setEmail("user" + i +"@gmail.com");
            user.setPassword("parola" + i);
            listOfUsers.add(user);
        }
        return listOfUsers;
    }
}
