
package View.Login;

import java.util.List;
import java.util.Scanner;
import Model.User;

public class UserListView {
    private Scanner scanner;

    public UserListView() {
        scanner = new Scanner(System.in);
    }

    public void displayUserList(List<User> users) {
        System.out.println("== Daftar Pengguna ==");
        System.out.format("%-10s%-20s%-20s\n", "ID", "Username", "Password");
        for (User user : users) {
            System.out.format("%-10s%-20s%-20s\n", user.getUserId(), user.getUsername(), user.getPassword());
        }
    }

    public int getUserChoice() {
        System.out.println("Pilih pengguna:");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Membuang karakter newline

        return choice;
    }
}
