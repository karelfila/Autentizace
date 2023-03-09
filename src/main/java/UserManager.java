import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import org.apache.commons.codec.cli.Digest;
import org.apache.commons.codec.digest.DigestUtils;

import javax.swing.*;
import java.io.IOException;
import java.io.Serializable;
import java.sql.*;
import java.time.LocalDateTime;

@Named
@SessionScoped
public class UserManager implements Serializable {

    private final String NaCl = "#&@{GetFuckedLooser}@&#";
    public void addUser(User user) {

        String NaCl = "#&@{GetFuckedLooser}@&#";

        try {
            Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost/users?user=root&password=");

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO user (fullName, email, hashedPassword, createdAt, updatedAt) VALUES(?,?,?, now(), now())"
            );

            preparedStatement.setString(1, user.getFullName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, DigestUtils.sha3_256Hex(user.getPassword() + NaCl));


            preparedStatement.execute();

            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public User getUser(String email) {



        try {
            Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost/users?user=root&password=");

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select u.userId, u.fullName, u.email ,u.hashedPassword, u.createdAt, u.updatedAt from user as u where u.email = ?"
            );

            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return new User(
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(1),
                        resultSet.getString(5),
                        resultSet.getString(6)
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private User loggedUser = new User();
    public void arePasswordsEqual(String email, String password) throws IOException {
        User user = getUser(email);
        if (user.getPassword().equals(DigestUtils.sha3_256Hex(password + NaCl))) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("hidden.xhtml");
            loggedUser.setLogged(true);
        }
    }


    public boolean before() throws IOException {
        if(loggedUser.isLogged()) {
           return true;
        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
            return false;
        }
    }
}
