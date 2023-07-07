package View.Login;

import java.util.Scanner;
import Model.User;
import UseCase.LoginUseCase;

public class LoginView {
    private Scanner scanner;
    private LoginUseCase loginUseCase;

    public LoginView(LoginUseCase loginUseCase) {
        scanner = new Scanner(System.in);
        this.loginUseCase = loginUseCase;
    }

    public void displayLoginScreen() {
        System.out.println("== Masuk ==");
        System.out.print("Username: ");
        User user = getUserInput();
        boolean isValid = loginUseCase.loginValidate(user);
        if (isValid) {
            loginUseCase.loginPassed();
        } else {
            System.out.println("Username atau Kata Sandi salah. Coba lagi.");
            displayLoginScreen();
        }
    }

    public User getUserInput() {
        String username = scanner.nextLine();
        System.out.print("Kata Sandi: ");
        String password = scanner.nextLine();

        return new User(null, username, password); // userId tidak perlu untuk proses login
    }
}
