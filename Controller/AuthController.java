package Controller;

import model.Database;
import model.User;

public class AuthController {
    public User login(String email, String password) {
        // Business Rule:
        if (!isValidEmail(email)) {
            return null;
        }

        User user = Database.users.get(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email != null && email.matches(emailRegex);
    }
}