import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.util.Date;

@Named
@RequestScoped
public class User {


    private String fullName;
    private String email;
    private String password;
    private int userID;
    private String createdAt;
    private String updatedAt;
    private boolean isLogged;



    public User() {
    }

    public User(String fullName, String email, String password, int userID, String createdAt, String updatedAt) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.userID = userID;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
