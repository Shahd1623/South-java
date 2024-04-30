package core;

//For Date handling
import java.sql.Date;

public class User {
    private int userId; // Primary key
    private String username; // Nullable, corresponds to VARCHAR(45)
    private String email; // Nullable
    private String password; // Nullable
    private Date registrationDate; // Nullable DATE

    // Default constructor
    public User() {}

    // Parameterized constructor for initialization
    public User(int userId, String username, String email, String password, Date registrationDate) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.registrationDate = registrationDate;
    }

    // Getters and setters for encapsulation
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}

