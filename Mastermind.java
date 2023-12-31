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
        System.out.println(" === Welcome to the Mastermind Game! === ");
        System.out.println("Here are the rules:\n\nGuess a sequence of the characters only using the characters here:\n" + TEST_LIST + "\n\nType 'stop' if you want to stop playing before game over\nType 'guess' if you want to see how many attempts you have left\n\nWhite peg - w - means color in the sequence but not right position\nRed Peg - r - means both color in the sequence & in right position\n\nPegs will not be presented in any order\n\nEasy Difficulty - No Dupes, 8 attempts, 6 Letters\nMedium Difficulty - Dupes, 8 attempts, 6 Letters\nHard Difficulty - Dupes, 8 attempts, 8 Letters\n");
        newGame();
    }
    // functions
    public static void mastermind (String difficulty, List<Character> answers, int attempts) {
        Scanner scan = new Scanner (System.in);
        List<Character> clues = new ArrayList<>();
        if (attempts == 8) {
            System.out.println("\nGame over! The right answer was:\n" + answers);
            System.out.println("Do you want to play again?");
            String response = scan.nextLine();
            if (response.equals("yes")) {
                newGame();
            }
            else {
                System.out.println("Thank you for playing!!!");
                System.out.println(" === GAME OVER === ");
            }
        }
        else {
            if((attempts + 1) == 8) {
                System.out.println("Last guess!");
            }
            System.out.println("\nGuess Here:");
            Boolean allLetters = true;
            String guess = scan.nextLine();
            for (int i = 0; i < guess.length(); i++) {
                char c = guess.charAt(i);
                if (!Character.isLetter(c)) {
                    allLetters = false;
                    break;
                }
            }
            if (guess.toLowerCase().equals("stop")) {
                System.out.println("\nThe right answer was:\n" + answers);
                System.out.println("Thank you for playing!!!");
                System.out.println(" === GAME OVER === ");
            }
            else if (guess.toLowerCase().equals("guess")) {
                System.out.println("You have " + (8 - attempts) + " attempts left!");
                mastermind(difficulty, answers, attempts);
            }
            else if ((guess.length() != 6 && (!difficulty.equals("hard"))) || (guess.length() != 8 && difficulty.equals("hard")) || allLetters == false) {
                attempts++;
                System.out.println("Only use letters 'ABCDEF' & remember the rules, you just used up one of your guesses. You have " + (8 - attempts) + " tries left!");
                mastermind(difficulty, answers, attempts);
            }
            else {
               char [] guessArr = guess.toCharArray();
               // Clues logic 
                System.out.println(answers);
                // System.out.println(guessArr); 
                Deque<Character> answersCopy = new ArrayDeque<>(answers);    
                // Langston's Modified Round() method               
                for (int i = 0; i < guessArr.length; i++) {
                    if (guessArr[i] == answers.get(i)) {
                        clues.add('r');
                        answersCopy.removeFirstOccurrence(guessArr[i]);
                    }
                }
                for (int i = 0; i < guessArr.length; i++) {
                    if (answersCopy.contains(guessArr[i])) {
                        clues.add('w');
                        answersCopy.removeFirstOccurrence(guessArr[i]);
                    }
                }
                // System.out.println(clues);
                Collections.shuffle(clues);
                System.out.println(clues);
                attempts++;
                if (!clues.contains('w') && (clues.size() == 6 || clues.size() == 8)) {
                    System.out.println("You win! It took you " + attempts + " tries!");
                    System.out.println("Do you want to play again?");
                    String response = scan.nextLine();
                    if (response.equals("yes")) {
                        newGame();
                    }
                    else {
                        System.out.println("Thank you for playing!!!");
                        System.out.println(" === GAME OVER === ");
                    }
                }
                else {
                    mastermind(difficulty, answers, attempts);
                } 
            }
        }
    }

    public static void difficultyChoice (String difficulty) {
        Scanner scan = new Scanner(System.in);
        if (difficulty.equals("hard")) {
            // System.out.println(1);
            mastermind("hard", randomSeqDupes(8), 0);
        } 
        else if (difficulty.equals("medium")) {
            // System.out.println(2);
            mastermind("medium", randomSeqDupes(6), 0);
        } 
        else if (difficulty.equals("easy")) {
            // System.out.println(3);
            mastermind("easy", randomSeqNoDupes(6), 0);
        }
        else if (difficulty.equals("stop")) {
            System.out.println("Thank you for playing!!!");
            System.out.println(" === GAME OVER === ");
        }
        else {
            System.out.println("Please input either easy, medium, or hard. Enter stop if done.");
            String newDiff = scan.nextLine();
            difficultyChoice(newDiff);
        }
    }

    public static List<Character> randomSeqDupes (int letters) {
        Random rand = new Random();
        List<Character> result = new ArrayList<>();
        while (result.size() < letters) {
            int randomIdx = rand.nextInt(TEST_LIST.size());
            result.add(TEST_LIST.get(randomIdx));
        }
        // System.out.println(result);
        return result;
    }

    public static List<Character> randomSeqNoDupes (int letters) {
        Random rand = new Random();
        List<Character> noDupes = new ArrayList<>(TEST_LIST);
        // System.out.println(noDupes);
        List<Character> result = new ArrayList<>();
        while (result.size() < letters) {
            if (noDupes.size() > 1) {
                int randomIdx = rand.nextInt(noDupes.size());
                result.add(noDupes.get(randomIdx));
                noDupes.remove(noDupes.get(randomIdx));
            }
            else {
                result.add(noDupes.get(0));
                // break;
            }
        }
        // System.out.println(result);
        return result;
    }
    public static void newGame () {
        Scanner scan = new Scanner (System.in);
        System.out.println("Choose Difficulty:\nEasy\t\tMedium\t\tHard");
        String difficulty = scan.nextLine().toLowerCase().strip();
        difficultyChoice(difficulty);
    }
}