package View.Rent;

import java.util.Scanner;
import java.sql.Date;
import java.time.LocalDate;
import Model.Rent;

public class AddRentView {
    private Scanner scanner;

    public AddRentView() {
        scanner = new Scanner(System.in);
    }

    public Rent getRentInput() {
        LocalDate currentDate = LocalDate.now();
        Date today = Date.valueOf(currentDate);

        System.out.println("Tanggal Sewa: " + today);
        System.out.print("ID Film: ");
        String movieId = scanner.nextLine();
        System.out.print("Penyewa: ");
        String renter = scanner.nextLine();

        return new Rent(null, today.toString(), "", movieId, renter, null);
    }
}
