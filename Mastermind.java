import java.util.*;
public class Mastermind {
    public static final String TEST_SEQ = "ABCDEF";
    public static void main(String [] args) {
        /*
        White peg - w - means color in the sequence but 
        not right position

        Red Peg - r - means both color in the sequence &
        in right position

        Easy Difficulty - No Dupes, 8 attempts, 6 Letters

        Medium Difficulty - Dupes, 8 attempts, 6 Letters

        Hard Difficulty - Dupes, 8 attempts, 8 Letters
        */
        Scanner scan = new Scanner(System.in);
        System.out.println(" === Welcome to the Mastermind Game! === ");
        System.out.println("Choose Difficulty:\nEasy\t\tMedium\t\tHard");
        String difficulty = scan.nextLine().toLowerCase().strip();
        difficultyChoice(difficulty);
    }
    public static void mastermind (int letters, boolean duplicates, String sequence) {

    }

    public static void difficultyChoice (String difficulty) {
        Scanner scan = new Scanner(System.in);
        if (difficulty.equals("hard")) {
            mastermind(8, true, TEST_SEQ);
        } 
        else if (difficulty.equals("medium")) {
            mastermind(6, true, TEST_SEQ);
        } 
        else if (difficulty.equals("easy")) {
            mastermind(6, false, TEST_SEQ);
        }
        else {
            System.out.println("Please input either easy, medium, or hard.");
            String newDiff = scan.nextLine();
            difficultyChoice(newDiff);
        }
    }
}