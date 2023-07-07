
package UseCase;

import java.util.ArrayList;
import java.util.List;
import Config.connection;
import Model.Movie;
import Model.Rent;
import View.Rent.ListRentView;
import View.Rent.AddRentView;
import View.Rent.ReturnRentView;
import java.sql.Date;
import java.time.LocalDate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RentUseCase {

    private List<Rent> activeRents;
    private ListRentView listRentView;
    private AddRentView addRentView;
    private ReturnRentView returnRentView;

    public RentUseCase() {
        activeRents = new ArrayList<>();
        listRentView = new ListRentView();
        addRentView = new AddRentView();
        returnRentView = new ReturnRentView();
    }

    public boolean addNewRent(Rent rent) {
        String query = "INSERT INTO rent (rent_date, return_date, id_movie, renter) VALUES (?, ?, ?, ?)";

        try (Connection conn = connection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, rent.getRentDate());
            stmt.setString(2, rent.getReturnDate());
            stmt.setString(3, rent.getMovieId());
            stmt.setString(4, rent.getRenter());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Error during rent addition.");
            ex.printStackTrace();
        }

        return false;
    }

    public List<Rent> getRents() {
        List<Rent> rents = new ArrayList<>();

        String query = "SELECT * FROM rent JOIN movie ON rent.id_movie = movie.id_movie";
        try (Connection conn = connection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String idRent = rs.getString("id_rent");
                String rentDate = rs.getString("rent_date");
                String returnDate = rs.getString("return_date");
                String movieId = rs.getString("id_movie");
                String title = rs.getString("title");
                String genre = rs.getString("genre");
                String renter = rs.getString("renter");

                Movie movie = new Movie(movieId, title, genre);
                Rent rent = new Rent(idRent, rentDate, returnDate, movieId, renter, movie);
                rent.setMovie(movie);
                rents.add(rent);
            }
        } catch (SQLException ex) {
            System.out.println("Error while fetching rents from database.");
            ex.printStackTrace();
        }

        return rents;
    }

    public void returnRent() {
        String rentId = returnRentView.getRentId();
        String query = "UPDATE rent SET return_date = ? WHERE id_rent = ?";

        try (Connection conn = connection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            LocalDate currentDate = LocalDate.now();
            Date today = Date.valueOf(currentDate);

            stmt.setDate(1, today);
            stmt.setString(2, rentId);

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("User telah mengembalikan movie");
                System.out.println("Status akan berubah menjadi Selesai");
            } else {
                System.out.println("Tidak ada film dengan ID tersebut.");
            }
        } catch (SQLException ex) {
            System.out.println("Error during movie update.");
            ex.printStackTrace();
        }
    }

}
