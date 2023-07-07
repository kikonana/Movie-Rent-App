
package View.Movie;

import java.util.Scanner;

public class DeleteMovieView {
    private Scanner scanner;

    public DeleteMovieView() {
        scanner = new Scanner(System.in);
    }

    public String getMovieId() {
        System.out.print("Masukkan ID Film yang akan dihapus: ");
        return scanner.nextLine();
    }
}