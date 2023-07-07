package Model;

public class Rent {

    private String id;
    private String rentDate;
    private String returnDate;
    private String movieId;
    private String renter;
    private Movie movie;

    public Rent(String id, String rentDate, String returnDate, String movieId, String renter, Movie movie) {
        this.id = id;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.movieId = movieId;
        this.renter = renter;
        this.movie = movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Movie getMovie() {
        return movie;
    }

    // Getter dan setter untuk atribut "id"
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Getter dan setter untuk atribut "rentDate"
    public String getRentDate() {
        return rentDate;
    }

    public void setRentDate(String rentDate) {
        this.rentDate = rentDate;
    }

    // Getter dan setter untuk atribut "returnDate"
    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    // Getter dan setter untuk atribut "movieId"
    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    // Getter dan setter untuk atribut "renter"
    public String getRenter() {
        return renter;
    }

    public void setRenter(String renter) {
        this.renter = renter;
    }
}
