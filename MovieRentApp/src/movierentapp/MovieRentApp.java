package movierentapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Model.User;
import UseCase.LoginUseCase;
import View.Login.LoginView;
import View.Login.UserListView;
import View.Login.AddUserView;
import View.Login.ChangePasswordView;
import View.Login.WelcomeView;
import UseCase.MovieUseCase;
import Model.Movie;
import Model.Rent;
import View.Movie.AddMovieView; // Import kelas AddMovieView
import View.Movie.MovieListView; // Import kelas MovieListView
import View.Movie.DeleteMovieView;
import View.Rent.AddRentView;
import View.Rent.ListRentView;
import View.Rent.ReturnRentView;
import UseCase.RentUseCase;

public class MovieRentApp {

    public static void main(String[] args) {
        // Objek-objek yang diperlukan
        LoginUseCase loginUseCase = new LoginUseCase();
        WelcomeView welcomeView = new WelcomeView();
        LoginView loginView = new LoginView(loginUseCase);
        UserListView userListView = new UserListView();
        AddUserView addUserView = new AddUserView();
        ChangePasswordView changePasswordView = new ChangePasswordView(loginUseCase);
        MenuView menuView = new MenuView();

        // Untuk operasi movie
        MovieUseCase movieUseCase = new MovieUseCase();
        AddMovieView addMovieView = new AddMovieView();

        // Untuk operasi rent
        RentUseCase rentUseCase = new RentUseCase();
        AddRentView addRentView = new AddRentView();

        // Menampilkan pesan selamat datang
        welcomeView.displayWelcomeMessage();

        // Login
        loginView.displayLoginScreen();

        boolean isRunning = true;
        while (isRunning) {
            // Menampilkan menu pilihan setelah login berhasil
            int menuChoice = menuView.displayMenu();

            // Mengecek pilihan menu dan melakukan tindakan sesuai pilihan
            switch (menuChoice) {
                case 1:
                    // Pilihan menu "Movie"
                    boolean movieMenuRunning = true;
                    while (movieMenuRunning) {
                        //movieUseCase.listMovies();
                        System.out.println("Menu Movie:");
                        System.out.println("1. Tambah Movie");
                        System.out.println("2. Hapus Movie");
                        System.out.println("3. Tampilkan Daftar Movie");
                        System.out.println("4. Kembali ke Menu Utama");
                        System.out.print("Pilih menu: ");
                        Scanner scanner = new Scanner(System.in);
                        int movieMenuChoice = scanner.nextInt();
                        switch (movieMenuChoice) {
                            case 1:
                                Movie movie = addMovieView.getMovieInput();
                                movieUseCase.addMovie(movie);
                                break;
                            case 2:
                                // Hapus Movie
                                movieUseCase.deleteMovie();
                                break;

                            case 3:
                                // Tampilkan Daftar Movie
                                MovieListView movieListView = new MovieListView();
                                List<Movie> movies = movieUseCase.getMovies();
                                movieListView.displayMovieList(movies);
                                break;
                            case 4:
                                // Kembali ke Menu Utama
                                movieMenuRunning = false;
                                break;
                            default:
                                System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                                break;
                        }
                    }
                    break;
                case 2:
                    // Pilihan menu "Rent"
                    boolean rentMenuRunning = true;
                    while (rentMenuRunning) {
                        System.out.println("Menu Rent:");
                        System.out.println("1. Tambah Rent");
                        System.out.println("2. Pengembalian Rent");
                        System.out.println("3. Tampilkan Daftar Rent");
                        System.out.println("4. Kembali ke Menu Utama");
                        System.out.print("Pilih menu: ");
                        Scanner scanner = new Scanner(System.in);
                        int rentMenuChoice = scanner.nextInt();
                        switch (rentMenuChoice) {
                            case 1:
                                // Tambah Rent
                                Rent rent = addRentView.getRentInput();
                                rentUseCase.addNewRent(rent);
                                break;
                            case 2:
                                // Hapus Rent
                                rentUseCase.returnRent();
                                break;
                            case 3:
                                // Tampilkan Daftar Rent
                                ListRentView listRentView = new ListRentView();
                                List<Rent> rents = rentUseCase.getRents();
                                listRentView.displayRentList(rents);
                                break;

                            case 4:
                                // Kembali ke Menu Utama
                                rentMenuRunning = false;
                                break;
                            default:
                                System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                                break;
                        }
                    }
                    break;

                case 3:
                    // Pilihan menu "User"
                    boolean userMenuRunning = true;
                    while (userMenuRunning) {
                        System.out.println("Menu User:");
                        System.out.println("1. Tambah User");
                        System.out.println("2. Ubah Password");
                        System.out.println("3. Tampilkan Daftar User");
                        System.out.println("4. Kembali ke Menu Utama");
                        System.out.print("Pilih menu: ");
                        Scanner scanner = new Scanner(System.in);
                        int userMenuChoice = scanner.nextInt();
                        switch (userMenuChoice) {
                            case 1:
                                // Tambah User
                                addUserView.displayAddUserScreen();
                                User user = addUserView.getUserInput();
                                boolean isUserAdded = loginUseCase.addUser(user);
                                if (isUserAdded) {
                                    System.out.println("Pengguna berhasil ditambahkan!");
                                } else {
                                    System.out.println("Gagal menambahkan pengguna. Silakan coba lagi.");
                                }
                                break;
                            case 2:
                                // Ubah Password
                                changePasswordView.displayChangePasswordScreen();
                                break;
                            case 3:
                                // Tampilkan Daftar User
                                List<User> users = loginUseCase.listUsers(); // metode ini perlu ditambahkan ke LoginUseCase
                                userListView.displayUserList(users);

                            case 4:
                                // Kembali ke Menu Utama
                                userMenuRunning = false;
                                break;
                            default:
                                System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                                break;
                        }
                    }
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                    break;
            }
        }
    }
}

class MenuView {

    public int displayMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Menu:");
        System.out.println("1. Movie");
        System.out.println("2. Rent");
        System.out.println("3. User");
        System.out.print("Pilih menu: ");
        int choice = scanner.nextInt();
        return choice;
    }
}
