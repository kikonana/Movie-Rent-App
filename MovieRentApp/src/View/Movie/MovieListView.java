
package View.Movie;

import java.util.List;
import java.util.Scanner;
import Model.Movie;

public class MovieListView {
    private Scanner scanner;

    public MovieListView() {
        scanner = new Scanner(System.in);
    }

    public void displayMovieList(List<Movie> movies) {
        System.out.println("== Daftar Film ==");
        System.out.format("%-10s%-20s%-20s\n", "ID", "Judul", "Genre");
        for (Movie movie : movies) {
            System.out.format("%-10s%-20s%-20s\n", movie.getId(), movie.getTitle(), movie.getGenre());
        }
    }

    public String getMovieChoice() {
        System.out.println("Pilih film:");
        String choice = scanner.nextLine();

        return choice;
    }
}
