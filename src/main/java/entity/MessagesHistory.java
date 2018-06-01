package entity;

import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "messagesHistory_table")
public class MessagesHistory {

    @Id
    private int messageId;
    
    private String senderEmail;
    private String message;
    
    @ManyToOne(cascade = {CascadeType.ALL})
    private User users; //FK

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "MessagesHistory{" + "messageId=" + messageId + ", senderEmail=" + senderEmail + ", message=" + message + '}' + "user = " + users ;
    }




}
