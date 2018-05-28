package Server;

/*
Modificam cu principii SOLID
sincronizarea = este pt performanta
 */
import dao.UserDaoInterface;
import entity.MessagesHistory;
import entity.User;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import persistance.MessagesHistoryDao;
import persistance.UserDao;
import service.MessagesHistoryImpl;
import service.UserImpl;

/**
 *
 * @author CeachiBogdan
 */
public class Server {

    public static void main(String[] args) {

        // insert some users and messages into Database
        List<User> listOfUsers = new UserDao().getAllAvailableUsers();
        List<MessagesHistory> listOfMessages = null;

        for (User user : listOfUsers) {
            listOfMessages = new MessagesHistoryDao().generateMessages(4, user); // generam cate 4 mesaje per user
            user.setMessages(listOfMessages);
            new UserImpl().insert(user);
        }

        UserDaoInterface usersImpl = new UserImpl();

        List<User> users = new UserImpl().findAll();

        users.forEach((x) -> System.out.println(x));

        System.out.println("Find by id ");

        User user = (User) usersImpl.findById(1);

        System.out.println(user);

        List<MessagesHistory> messages = new MessagesHistoryImpl().findAll();
        messages.forEach((x) -> System.out.println(x));

        try {

            while (true) {

                ServerThread st = ServerEngine.getInstance().connectClient();
                ClientsContainer.addClient(st);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
