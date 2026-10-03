package voting;

import java.util.HashMap;
import java.util.Map;

public class VotingSystem {

    // Store registered users
    private Map<String, User> users = new HashMap<>();

    // Store whether user has voted
    private Map<String, Boolean> userVoted = new HashMap<>();

    // Store candidates
    private Map<String, Candidate> candidates = new HashMap<>();

    // Store votes for each candidate
    private Map<String, Integer> candidateVotes = new HashMap<>();


    // ==========================================
    // REGISTER USER
    // ==========================================

    public void registerUser(String username, String password) {

        if (users.containsKey(username)) {

            System.out.println("Username already exists!");
            return;
        }

        User user = new User(username, password);

        users.put(username, user);

        // Initially user has not voted
        userVoted.put(username, false);

        System.out.println("Registration successful!");
    }


    // ==========================================
    // LOGIN
    // ==========================================

    public boolean login(String username, String password) {

        if (!users.containsKey(username)) {

            System.out.println("User not registered!");
            return false;
        }

        User user = users.get(username);

        if (user.getPassword().equals(password)) {

            System.out.println("Login successful!");
            return true;
        }

        System.out.println("Invalid password!");
        return false;
    }


    // ==========================================
    // ADD CANDIDATE
    // ==========================================

    public void addCandidate(String candidateName) {

        if (candidates.containsKey(candidateName)) {

            System.out.println("Candidate already exists!");
            return;
        }

        Candidate candidate = new Candidate(candidateName);

        candidates.put(candidateName, candidate);

        // Initially candidate has 0 votes
        candidateVotes.put(candidateName, 0);

        System.out.println(
                "Candidate " + candidateName
                + " added successfully!"
        );
    }


    // ==========================================
    // DISPLAY CANDIDATES
    // ==========================================

    public void displayCandidates() {

        System.out.println("\n===== CANDIDATE LIST =====");

        if (candidates.isEmpty()) {

            System.out.println("No candidates available.");
            return;
        }

        int number = 1;

        for (Candidate candidate : candidates.values()) {

            String name = candidate.getCandidateName();

            int votes = candidateVotes.get(name);

            System.out.println(
                    number + ". "
                    + name
                    + " - Votes: "
                    + votes
            );

            number++;
        }
    }


    // ==========================================
    // CAST VOTE
    // ==========================================

    public void castVote(String username, String candidateName) {

        // Check user
        if (!users.containsKey(username)) {

            System.out.println("User is not registered!");
            return;
        }

        // Check whether user already voted
        if (userVoted.get(username)) {

            System.out.println(
                    "You have already voted!"
            );

            return;
        }

        // Check candidate
        if (!candidates.containsKey(candidateName)) {

            System.out.println(
                    "Candidate not found!"
            );

            return;
        }

        // Get current votes
        int currentVotes = candidateVotes.get(candidateName);

        // Increase vote by 1
        currentVotes++;

        candidateVotes.put(
                candidateName,
                currentVotes
        );

        // Mark user as voted
        userVoted.put(username, true);

        System.out.println(
                "Vote cast successfully!"
        );
    }


    // ==========================================
    // DISPLAY TOTAL VOTES
    // ==========================================

    public void displayTotalVotes() {

        System.out.println("\n===== TOTAL VOTES =====");

        if (candidateVotes.isEmpty()) {

            System.out.println(
                    "No votes available."
            );

            return;
        }

        int totalVotes = 0;

        for (Map.Entry<String, Integer> entry
                : candidateVotes.entrySet()) {

            System.out.println(
                    entry.getKey()
                    + " : "
                    + entry.getValue()
                    + " votes"
            );

            totalVotes =
                    totalVotes + entry.getValue();
        }

        System.out.println("-------------------------");

        System.out.println(
                "Total Votes Cast: "
                + totalVotes
        );
    }


    // ==========================================
    // DISPLAY RESULT
    // ==========================================

    public void displayResult() {

        System.out.println("\n===== ELECTION RESULT =====");

        if (candidateVotes.isEmpty()) {

            System.out.println(
                    "No candidates available."
            );

            return;
        }

        String finalist = null;
        String runnerUp = null;

        int highestVotes = -1;
        int secondHighestVotes = -1;


        // Find finalist
        for (Map.Entry<String, Integer> entry
                : candidateVotes.entrySet()) {

            String candidateName = entry.getKey();
            int votes = entry.getValue();

            if (votes > highestVotes) {

                secondHighestVotes = highestVotes;
                runnerUp = finalist;

                highestVotes = votes;
                finalist = candidateName;
            }
            else if (votes > secondHighestVotes) {

                secondHighestVotes = votes;
                runnerUp = candidateName;
            }
        }


        // Display finalist
        System.out.println(
                "Finalist/Winner: "
                + finalist
                + " - "
                + highestVotes
                + " votes"
        );


        // Display runner-up
        if (runnerUp != null) {

            System.out.println(
                    "Runner-Up: "
                    + runnerUp
                    + " - "
                    + secondHighestVotes
                    + " votes"
            );

        } else {

            System.out.println(
                    "Runner-Up: Not available"
            );
        }
    }
}