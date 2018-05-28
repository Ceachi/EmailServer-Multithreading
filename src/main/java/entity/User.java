
package entity;

import java.util.*;
import javax.persistence.*;



@Entity
@Table(name = "user_table")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // make it autoIncrement
    @Column(name = "id", updatable = false, nullable = false)
    private int userId;
    
    private String email;
    private String password;
    
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "users", cascade = {CascadeType.ALL})
    private List<MessagesHistory> messages = new ArrayList<>();
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<MessagesHistory> getMessages() {
        return messages;
    }

    public void setMessages(List<MessagesHistory> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", email=" + email + ", password=" + password;
    }
}
