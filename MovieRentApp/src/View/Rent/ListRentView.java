package View.Rent;

import java.util.List;
import Model.Rent;

public class ListRentView {
    public void displayRentList(List<Rent> rentList) {
        if (rentList.isEmpty()) {
            System.out.println("Tidak ada sewa aktif.");
        } else {
            System.out.println("== Daftar Sewa Aktif ==");
            System.out.format("%-10s%-15s%-20s%-10s%-15s%-10s%-10s%-10s\n", "ID Rent", "Rent_date", "Return_date", "ID Movie", "Title", "Genre", "Renter", "Status");
            for (Rent rent : rentList) {
                String status = (rent.getReturnDate() != null && !rent.getReturnDate().isEmpty()) ? "Selesai" : "Rent Aktif";
                System.out.format("%-10s%-15s%-20s%-10s%-15s%-10s%-10s%-10s\n", rent.getId(), rent.getRentDate(), rent.getReturnDate(), rent.getMovieId(), rent.getMovie().getTitle(), rent.getMovie().getGenre(), rent.getRenter(), status);
            }
        }
    }
}
