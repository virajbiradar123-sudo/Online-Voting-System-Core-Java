package movieticketbooking;

import java.util.Scanner;

public class MovieTicketBookingSystem {

    // 2D boolean array for seats
    // false = Available
    // true = Booked
    static boolean[][] seats = new boolean[5][6];

    static Scanner sc = new Scanner(System.in);

    // Movie details
    static String movieName = "Avengers: Endgame";
    static double ticketPrice = 200.0;
    static double movieRating = 4.5;

    // Method to display available seats
    static void showSeats() {

        System.out.println("\n========== SEAT LAYOUT ==========");
        System.out.println("     1   2   3   4   5   6");

        for (int i = 0; i < seats.length; i++) {

            System.out.print("Row " + (i + 1) + " ");

            for (int j = 0; j < seats[i].length; j++) {

                if (seats[i][j]) {
                    System.out.print("[X] ");
                } else {
                    System.out.print("[O] ");
                }
            }

            System.out.println();
        }

        System.out.println("\n[O] = Available");
        System.out.println("[X] = Booked");
    }

    // Method to book ticket
    static void bookTicket() {

        showSeats();

        System.out.print("\nEnter row number (1-5): ");
        int row = sc.nextInt();

        System.out.print("Enter seat number (1-6): ");
        int seat = sc.nextInt();

        // Condition checking
        if (row < 1 || row > 5 || seat < 1 || seat > 6) {

            System.out.println("Invalid seat number!");

            return;
        }

        // Convert to array index
        row = row - 1;
        seat = seat - 1;

        // Check whether seat is already booked
        if (seats[row][seat]) {

            System.out.println("Sorry! This seat is already booked.");

            return;
        }

        System.out.print("Enter number of tickets: ");
        int numberOfTickets = sc.nextInt();

        if (numberOfTickets <= 0) {

            System.out.println("Invalid number of tickets.");

            return;
        }

        double totalAmount = ticketPrice * numberOfTickets;

        // Weekend pricing
        System.out.print("Is today a weekend? (yes/no): ");
        String weekend = sc.next();

        if (weekend.equalsIgnoreCase("yes")) {

            totalAmount = totalAmount + (totalAmount * 0.20);

            System.out.println("Weekend pricing applied: 20% extra");
        }

        // Discount coupon
        System.out.print("Do you have a discount coupon? (yes/no): ");
        String coupon = sc.next();

        if (coupon.equalsIgnoreCase("yes")) {

            System.out.print("Enter coupon code: ");
            String couponCode = sc.next();

            if (couponCode.equalsIgnoreCase("MOVIE10")) {

                double discount = totalAmount * 0.10;

                totalAmount = totalAmount - discount;

                System.out.println("Coupon applied: 10% discount");

            } else {

                System.out.println("Invalid coupon code.");
            }
        }

        // Book the selected seat
        seats[row][seat] = true;

        System.out.println("\n========== BOOKING SUCCESSFUL ==========");
        System.out.println("Movie Name     : " + movieName);
        System.out.println("Seat           : Row " + (row + 1) +
                           ", Seat " + (seat + 1));
        System.out.println("Tickets        : " + numberOfTickets);
        System.out.println("Ticket Price   : Rs." + ticketPrice);
        System.out.println("Total Amount   : Rs." + totalAmount);
        System.out.println("Movie Rating   : " + movieRating + "/5");

    }

    // Method to cancel ticket
    static void cancelTicket() {

        showSeats();

        System.out.print("\nEnter row number to cancel (1-5): ");
        int row = sc.nextInt();

        System.out.print("Enter seat number to cancel (1-6): ");
        int seat = sc.nextInt();

        if (row < 1 || row > 5 || seat < 1 || seat > 6) {

            System.out.println("Invalid seat number!");

            return;
        }

        row = row - 1;
        seat = seat - 1;

        // Check whether seat is booked
        if (seats[row][seat]) {

            seats[row][seat] = false;

            System.out.println("\nTicket cancelled successfully.");
            System.out.println("Row: " + (row + 1));
            System.out.println("Seat: " + (seat + 1));

        } else {

            System.out.println("This seat is not booked.");
        }
    }

    // Method to display movie details
    static void movieDetails() {

        System.out.println("\n========== MOVIE DETAILS ==========");
        System.out.println("Movie Name   : " + movieName);
        System.out.println("Ticket Price : Rs." + ticketPrice);
        System.out.println("Movie Rating : " + movieRating + "/5");
        System.out.println("Total Rows   : 5");
        System.out.println("Seats/Row    : 6");
        System.out.println("Total Seats  : 30");
    }

    // Method to display available seat count
    static void availableSeats() {

        int count = 0;

        for (int i = 0; i < seats.length; i++) {

            for (int j = 0; j < seats[i].length; j++) {

                if (!seats[i][j]) {
                    count++;
                }
            }
        }

        System.out.println("\nAvailable Seats: " + count);
        System.out.println("Booked Seats   : " + (30 - count));
    }

    // Main method
    public static void main(String[] args) {

        int choice;

        System.out.println("======================================");
        System.out.println("     MOVIE TICKET BOOKING SYSTEM");
        System.out.println("======================================");

        do {

            System.out.println("\n========== MAIN MENU ==========");
            System.out.println("1. Show Available Seats");
            System.out.println("2. Book Ticket");
            System.out.println("3. Cancel Ticket");
            System.out.println("4. Movie Details");
            System.out.println("5. Show Seat Count");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    showSeats();
                    break;

                case 2:
                    bookTicket();
                    break;

                case 3:
                    cancelTicket();
                    break;

                case 4:
                    movieDetails();
                    break;

                case 5:
                    availableSeats();
                    break;

                case 6:
                    System.out.println("\nThank you for using Movie Ticket Booking System!");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }

        } while (choice != 6);

        sc.close();
    }
}