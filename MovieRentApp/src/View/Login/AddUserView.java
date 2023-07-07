package View.Login;

import java.util.Scanner;
import Model.User;

public class AddUserView {
    private Scanner scanner;

    public AddUserView() {
        scanner = new Scanner(System.in);
    }

    public void displayAddUserScreen() {
        System.out.println("== Tambah Pengguna Baru ==");
        System.out.print("Nama Pengguna: ");
    }

    public User getUserInput() {
        String username = scanner.nextLine();
        System.out.print("Kata Sandi: ");
        String password = scanner.nextLine();

        return new User(null, username, password); // null karena kita belum mempunyai userId
    }
}
