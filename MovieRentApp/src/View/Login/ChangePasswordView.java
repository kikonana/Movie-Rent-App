package View.Login;

import java.util.Scanner;
import Model.User;
import UseCase.LoginUseCase;

public class ChangePasswordView {
    private Scanner scanner;
    private LoginUseCase loginUseCase;

    public ChangePasswordView(LoginUseCase loginUseCase) {
        this.scanner = new Scanner(System.in);
        this.loginUseCase = loginUseCase;
    }

    public void displayChangePasswordScreen() {
        System.out.println("== Ubah Kata Sandi Pengguna ==");
        String userId = getUserIdInput();
        User userForPasswordChange = getUserInput();
        String newPassword = getNewPasswordInput();
        boolean isPasswordChanged = loginUseCase.changePassword(userId, userForPasswordChange, newPassword);
        
        if (isPasswordChanged) {
            System.out.println("Password berhasil diubah!");
        } else {
            System.out.println("Gagal mengubah password. Silakan coba lagi.");
        }
    }

    private String getUserIdInput() {
        System.out.print("ID Pengguna: ");
        return scanner.nextLine();
    }

    private User getUserInput() {
        System.out.print("Nama Pengguna: ");
        String username = scanner.nextLine();
        System.out.print("Kata Sandi Lama: ");
        String oldPassword = scanner.nextLine();

        return new User(null, username, oldPassword);
    }

    private String getNewPasswordInput() {
        System.out.print("Kata Sandi Baru: ");
        return scanner.nextLine();
    }
}
