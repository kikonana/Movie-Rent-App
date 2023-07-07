
package UseCase;

import Config.connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.Movie;
import View.Movie.MovieListView;
import View.Movie.AddMovieView;
import View.Movie.DeleteMovieView;

public class MovieUseCase {

    private List<Movie> movies;
    private MovieListView movieListView;
    private AddMovieView addMovieView;
    private DeleteMovieView deleteMovieView;

    public MovieUseCase() {
        movies = new ArrayList<>();
        movieListView = new MovieListView();
        addMovieView = new AddMovieView();
        deleteMovieView = new DeleteMovieView();
    }

    public List<Movie> getMovies() {
        List<Movie> movies = new ArrayList<>();

        String query = "SELECT * FROM movie";
        try (Connection conn = connection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String id = rs.getString("id_movie");
                String title = rs.getString("title");
                String genre = rs.getString("genre");

                movies.add(new Movie(id, title, genre));
            }

        } catch (SQLException ex) {
            System.out.println("Error while fetching movies from database.");
            ex.printStackTrace();
        }

        return movies;
    }

    public boolean addMovie(Movie movie) {
        String query = "INSERT INTO movie (title, genre) VALUES (?, ?)";

        try (Connection conn = connection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getGenre());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Error during movie addition.");
            ex.printStackTrace();
        }

        return false;
    }

    public void deleteMovie() {
        String movieId = deleteMovieView.getMovieId();

        String query = "DELETE FROM movie WHERE id_movie = ?";

        try (Connection conn = connection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, movieId);

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Film berhasil dihapus.");
            } else {
                System.out.println("Tidak ada film dengan ID tersebut.");
            }
        } catch (SQLException ex) {
            System.out.println("Error during movie deletion.");
            ex.printStackTrace();
        }
    }

}
