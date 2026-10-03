package voting;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        VotingSystem votingSystem = new VotingSystem();


        // ==========================================
        // ADD CANDIDATES
        // ==========================================

        votingSystem.addCandidate("Rahul");
        votingSystem.addCandidate("Priya");
        votingSystem.addCandidate("Amit");


        int choice = 0;


        // ==========================================
        // MAIN MENU
        // ==========================================

        do {

            System.out.println("\n================================");
            System.out.println("       ONLINE VOTING SYSTEM");
            System.out.println("================================");
            System.out.println("1. Register");
            System.out.println("2. Login and Vote");
            System.out.println("3. View Candidate List");
            System.out.println("4. View Total Votes");
            System.out.println("5. View Election Result");
            System.out.println("6. Exit");
            System.out.println("================================");

            System.out.print("Enter your choice: ");


            try {

                choice = scanner.nextInt();

                scanner.nextLine();


                switch (choice) {


                    // ==================================
                    // REGISTER
                    // ==================================

                    case 1:

                        System.out.println(
                                "\n===== REGISTRATION ====="
                        );

                        System.out.print(
                                "Enter username: "
                        );

                        String username =
                                scanner.nextLine();


                        System.out.print(
                                "Enter password: "
                        );

                        String password =
                                scanner.nextLine();


                        votingSystem.registerUser(
                                username,
                                password
                        );

                        break;


                    // ==================================
                    // LOGIN AND VOTE
                    // ==================================

                    case 2:

                        System.out.println(
                                "\n===== LOGIN ====="
                        );


                        System.out.print(
                                "Enter username: "
                        );

                        String loginUsername =
                                scanner.nextLine();


                        System.out.print(
                                "Enter password: "
                        );

                        String loginPassword =
                                scanner.nextLine();


                        boolean loginSuccess =
                                votingSystem.login(
                                        loginUsername,
                                        loginPassword
                                );


                        if (loginSuccess) {

                            votingSystem.displayCandidates();


                            System.out.print(
                                    "Enter candidate name to vote: "
                            );

                            String candidateName =
                                    scanner.nextLine();


                            votingSystem.castVote(
                                    loginUsername,
                                    candidateName
                            );
                        }

                        break;


                    // ==================================
                    // VIEW CANDIDATES
                    // ==================================

                    case 3:

                        votingSystem.displayCandidates();

                        break;


                    // ==================================
                    // VIEW TOTAL VOTES
                    // ==================================

                    case 4:

                        votingSystem.displayTotalVotes();

                        break;


                    // ==================================
                    // VIEW RESULT
                    // ==================================

                    case 5:

                        votingSystem.displayResult();

                        break;


                    // ==================================
                    // EXIT
                    // ==================================

                    case 6:

                        System.out.println(
                                "\nThank you for using "
                                + "Online Voting System!"
                        );

                        break;


                    // ==================================
                    // INVALID CHOICE
                    // ==================================

                    default:

                        System.out.println(
                                "Invalid choice! "
                                + "Please enter 1 to 6."
                        );
                }


            } catch (InputMismatchException e) {

                System.out.println(
                        "Invalid input! "
                        + "Please enter a number."
                );

                scanner.nextLine();

                choice = 0;
            }


        } while (choice != 6);


        scanner.close();
    }
}