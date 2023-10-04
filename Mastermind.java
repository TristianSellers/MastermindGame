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
        // we could implement logic explaining but im not sure its important
        Scanner scan = new Scanner(System.in);
        System.out.println(" === Welcome to the Mastermind Game! === ");
        System.out.println("Choose Difficulty:\nEasy\t\tMedium\t\tHard");
        String difficulty = scan.nextLine().toLowerCase().strip();
        difficultyChoice(difficulty);
    }
    public static void mastermind (int letters, boolean duplicates,List<Character> random_arr) {
        String answer = "";
        List<Character> list = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        Boolean Game_on = true;
        int attempts = 8;
        for(int i = 0; i < attempts; i++){
            System.out.println("Enter your guess");
            answer = scan.nextLine();
            answer = answer.toUpperCase();
            //System.out.println(answer);
            char[] arr = answer.toCharArray();
            for(char y: arr) {
                list.add(y);
            }
            String result = Round(list,random_arr);
            result = result.toLowerCase();
            //System.out.println(list);
            System.out.println(result);
            if(result.equals("rrrrrr") || result.equals("rrrrrrrr")){
                System.out.println("You won!");
                break;
            }
              else {
                System.out.println("Try Again");
            } 
            answer = "";
            list.clear();
            }

        }
      System.out.println("Would you like to play again? Choose a difficulty");
      String difficulty1 = scan.nextLine();
      difficulty1 = difficulty1.toLowerCase();
      if(difficulty1.equals("easy") || difficulty1.equals("medium") || difficulty1.equals("hard")) {
        difficultyChoice(difficulty1);
      }

       

    }
    public static String Round(List<Character> list, List<Character> random_arr) {
        System.out.println(random_arr);
        int count = 0;
        String result = "";
        for(Character x:random_arr){
            Character c = list.get(count);
            if(c == random_arr.get(count)){
                result += "r";
            }
            else if(random_arr.contains(c)){
                result += "w";
            }
            count++;
        }
        return result;
    }

    public static void difficultyChoice (String difficulty) {
        Scanner scan = new Scanner(System.in);
        if (difficulty.equals("hard")) {
            mastermind(8, true, randomSeqDupes(8));
        } 
        else if (difficulty.equals("medium")) {
            mastermind(6, true, randomSeqDupes(6));
        } 
        else if (difficulty.equals("easy")) {
            mastermind(6, false, randomSeqNoDupes(6));
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
        while (result.size() < letters) {
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
        while (result.size() < letters) {
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