import Controller.AppController;
import Controller.AuthController;
import view.LoginView;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                LoginView loginView = new LoginView();
                AuthController authController = new AuthController();
                
                
                new AppController(loginView, authController);
                
               
                loginView.setVisible(true);
            }
        });
    }
}