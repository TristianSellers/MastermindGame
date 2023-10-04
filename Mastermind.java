import java.util.*;
public class Mastermind {
    public static final List<Character> TEST_LIST = new ArrayList<>(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F'));
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
    // functions
    public static void mastermind (List<Character> sequence) {

    }

    public static void difficultyChoice (String difficulty) {
        Scanner scan = new Scanner(System.in);
        if (difficulty.equals("hard")) {
            // System.out.println(1);
            mastermind(randomSeqDupes(8));
        } 
        else if (difficulty.equals("medium")) {
            // System.out.println(2);
            mastermind(randomSeqDupes(6));
        } 
        else if (difficulty.equals("easy")) {
            // System.out.println(3);
            mastermind(randomSeqNoDupes(6));
        }
        else {
            System.out.println("Please input either easy, medium, or hard.");
            String newDiff = scan.nextLine();
            difficultyChoice(newDiff);
        }
    }

    public static List<Character> randomSeqDupes (int letters) {
        Random rand = new Random();
        List<Character> result = new ArrayList<>();
        while (result.size() <= letters) {
            int randomIdx = rand.nextInt(TEST_LIST.size());
            result.add(TEST_LIST.get(randomIdx));
        }
        return result;
    }

    public static List<Character> randomSeqNoDupes (int letters) {
        Random rand = new Random();
        List<Character> noDupes = new ArrayList<>(TEST_LIST);
        // System.out.println(noDupes);
        List<Character> result = new ArrayList<>();
        while (result.size() <= letters) {
            if (noDupes.size() > 1) {
                int randomIdx = rand.nextInt(noDupes.size());
                result.add(noDupes.get(randomIdx));
                noDupes.remove(noDupes.get(randomIdx));
            }
            else {
                result.add(noDupes.get(0));
            }
        }
        // System.out.println(1);
        return result;
    }
}