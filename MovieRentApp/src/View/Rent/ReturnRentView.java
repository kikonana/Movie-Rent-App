
package View.Rent;

import java.util.Scanner;

public class ReturnRentView {
    private Scanner scanner;

    public ReturnRentView() {
        scanner = new Scanner(System.in);
    }

    public String getRentId() {
        System.out.print("Masukkan ID Sewa yang akan dikembalikan: ");
        return scanner.nextLine();
    }
}
