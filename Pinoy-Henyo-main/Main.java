import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

interface points{
    
    public void points();
    public void start();
    
}
class Questioner {
    private String wordGuess;
    private String category;
    private int letterSize;
    private int wordSize;
    private int difficultyLevel;

    protected Questioner(String wordGuess, String category, int letterSize, int wordSize, int difficultyLevel) {
        this.wordGuess = wordGuess;
        this.category = category;
        this.letterSize = letterSize;
        this.wordSize = wordSize;
        this.difficultyLevel = difficultyLevel;
    }

    public void setGuess(String guess) {
        this.wordGuess = guess.toLowerCase();
    }

    public String getGuess() {
        return this.wordGuess;
    }

    public void setCategory(String category) {
        String categoryUpper = category.toUpperCase();
        if (categoryUpper.equals("PERSON") || categoryUpper.equals("PLACE") ||
            categoryUpper.equals("ANIMAL") || categoryUpper.equals("FOOD") ||
            categoryUpper.equals("THING")) {
            this.category = categoryUpper;
        } else {
            System.out.println("INVALID CATEGORY! ENTER A VALID CATEGORY");
            Scanner scanner = new Scanner(System.in);
            String ret = scanner.nextLine();
            setCategory(ret);
        }
    }

    public String getCategory() {
        return this.category.toUpperCase();
    }

    public void setLetterSize(String wordGuess) {
        int count = 0;
        for (char c : wordGuess.toCharArray()) {
            if (!Character.isWhitespace(c)) {
                count++;
            }
        }
        this.letterSize = count;
        System.out.println("Letter size: " + count);
    }

    public int getLetterSize() {
        return this.letterSize;
    }

    public void setWordSize(String wordGuess) {
        int count = wordGuess.split("\\s+").length;
        this.wordSize = count;
        System.out.println("Word size: " + count);
    }

    public int getWordSize() {
        return this.wordSize;
    }

    public int getDifficultyLevel() {
        return this.difficultyLevel;
    }

    protected void inputFunc() {
        Scanner scanner = new Scanner(System.in);
        difficultyLevel = 0;
        System.out.println("\n=======================================");
        System.out.println("\t      PINOY HENYO");
        System.out.println("=======================================");
        System.out.println("\t\t[1] Easy");
        System.out.println("\t\t[2] Medium");
        System.out.println("\t\t[3] Hard");
        System.out.println("=======================================");
        while (difficultyLevel < 1 || difficultyLevel > 3) {
                System.out.print("Enter difficulty level: ");
                difficultyLevel = scanner.nextInt();
                if (difficultyLevel < 1 || difficultyLevel > 3) {
                System.out.println("Invalid input.");
                System.out.println("---------------------------------------");
                }
            }   
        System.out.println("---------------------------------------");
        System.out.print("Set word to guess: ");
        scanner.nextLine();
        String guess = scanner.nextLine();
        setGuess(guess.toUpperCase());
        System.out.print("Set Category (Person/Place/Animal/Food/Thing): ");
        String category = scanner.nextLine();
        System.out.println("---------------------------------------");
        setCategory(category);
        setLetterSize(getGuess());
        setWordSize(getGuess());
        System.out.println("=======================================");
        System.out.println("Press enter for the guesser's turn!");
        scanner.nextLine();
        System.out.println("\033[H\033[2J");
    }
}
class Guesser extends Questioner implements points {
    private String guess;
    private String CatGuess;
    private long psec;
    public Guesser(String wordGuess, String category, int letterSize, int wordSize, String guess, int difficultyLevel) {
        super(wordGuess, category, letterSize, wordSize, difficultyLevel);
        this.guess = guess;
        this.CatGuess = CatGuess;
        this.psec = 0;
    }

    private void setAnswer(String answer) {
        this.guess = answer.toLowerCase();
    }

    private String getAnswer() {
        return this.guess;
    }
    
     private void getsetcatans(String broly){
        this.CatGuess = broly.toLowerCase();
    }
    
    private String getsetcatans(){
        return this.CatGuess;
    }

    public void start() {
        inputFunc();
        gameBegin();
    }
public void spsec(long sec2){
    this.psec = sec2;
}

public long gpsec(){
    return this.psec;
}
public void points(){
    int x = getDifficultyLevel();
    long y = gpsec();
    long z = x*y*100;
    System.out.println("Your score adds up to: "+z+"!");
    
}

    public void gameBegin() {
        Scanner scanner = new Scanner(System.in);
        Timer timer = new Timer();

        System.out.println("=======================================");
        System.out.println("\t      PINOY HENYO");
        System.out.println("=======================================");
        
        int timeLimit;
        switch (getDifficultyLevel()) {
            case 1:
                timeLimit = 90;
                break;
            case 2:
                timeLimit = 60;
                break;
            case 3:
                timeLimit = 30;
                break;
            default:
                timeLimit = 60;
                break;
        }
        timer.scheduleAtFixedRate(new TimerTask() {
            long sec = timeLimit;

            public void run() {
                sec--;
                spsec(sec);
                if (sec == 75 ||sec == 45 || sec == 30 || sec == 15) {
                    System.out.println("\n=======================================");
                    System.out.println("\t" + sec + " Seconds remaining!");
                    System.out.println("=======================================");
                }
                if (sec == 0) {
                    System.out.println("\n=======================================");
                    System.out.println("   Time's up! The word was: " + getGuess());
                    System.out.println("=======================================");
                    timer.cancel();
                    playAgain();
                }
            }
        }, 0, 1000);

 
        System.out.println("---------------------------------------");
        boolean isGameOver = false;
        
        while(!isGameOver){
           System.out.println("Category guess!: ");
                String catans = scanner.nextLine();
                getsetcatans(catans);
                
            if(getsetcatans().equalsIgnoreCase(getCategory())){
                System.out.println("Right!");
                break;
            }
            
            else{
                System.out.println("No!");
            }
       }
        
        while (!isGameOver) {
            System.out.print("Input word guess: ");
            String answer = scanner.nextLine();
            setAnswer(answer);

            if (getGuess().equalsIgnoreCase(getAnswer())) {
                System.out.println("=======================================");
                System.out.println("Congratulations! You Win!");
                timer.cancel();
                points();
                isGameOver = true;
                playAgain();
            } else {
                System.out.println("...Not Quite!");
                System.out.println("---------------------------------------");
            }
        }
    }

public void playAgain() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=======================================");
        System.out.print("Do you want to play again? (Y/N): ");
        String answer = scanner.nextLine();
        switch (answer.toUpperCase()) {
            case "Y":
                start();
                break;
            case "N":
                System.out.println("=======================================");
                System.out.println("Thanks for playing!");
                System.out.println("=======================================");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid input.");
                playAgain();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAnotherGame = true;

        while (playAnotherGame) {
            Guesser guesser = new Guesser("", "", 0, 0, "", 0); // Initialize difficultyLevel as 0 for now
            guesser.start();
            System.out.print("Do you want to play again (Y/N)?  ");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("N")) {
                playAnotherGame = false;
            }
        }
        scanner.close();
    }
}

