
package View.Movie;

import java.util.Scanner;
import Model.Movie;

public class AddMovieView {
    private Scanner scanner;

    public AddMovieView() {
        scanner = new Scanner(System.in);
    }

    public Movie getMovieInput() {
        System.out.print("ID Film: ");
        String id = scanner.nextLine();
        System.out.print("Judul: ");
        String title = scanner.nextLine();
        System.out.print("Genre: ");
        String genre = scanner.nextLine();

        return new Movie(id, title, genre);
    }
}