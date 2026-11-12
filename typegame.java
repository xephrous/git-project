import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
//import static pkginterface.TextGame.highScore;

//Three interfaces
interface RhythmGame{
    void startGame();
    void showLevel(int lvl);
    void finishGame(ArrayList<Double> times);
}

interface Sequence{
    String generateSequence();
    boolean checkInput(String sequence, String answer);
}

interface Highscore{
    double getFastTime(long start, long end);
    void displayResult(ArrayList<Double> times);
}

public class SecondRhythm implements RhythmGame, Sequence, Highscore{

    Scanner type = new Scanner(System.in);
    Random rng = new Random();

    ArrayList<Double> times = new ArrayList<>();
    
    int highScore = 0;
    int score = 0;

    public void menu() {

        displayHighScore();
        boolean loop = true;

        while (loop) {
            System.out.println("\n\tWelcome!");
            System.out.println("1. New Game");
            System.out.println("2. View High Score");
            System.out.println("3. Exit");
            System.out.print("Choose: ");

            String choice = type.nextLine();

            switch (choice) {
                case "1":
                    startGame();
                    break;

                case "2":
                    System.out.println("\n>>> Highscore <<<");
                    System.out.println("High Score: " + highScore);
                    System.out.println("\nPress ENTER to return.");
                    type.nextLine();
                    break;

                case "3":
                    System.out.println("\nThanks for playing!");
                    return;
//                    loop = false;
//                    type.close();

                default:
                    System.out.println("\nInvalid choice.");
                    break;
            }
        }
    }

    public void startGame() {
//
//        System.out.println("Welcome!");
//        System.out.println("Press ENTER to start.");
//        type.nextLine();

        score = 0;
        times.clear();

        System.out.println("Press ENTER to start.");
        type.nextLine();

        for (int lvl = 1; lvl <= 5; lvl++){
            showLevel(lvl);

            String sequence = generateSequence();

            System.out.println("+----------------+");
            System.out.println("                ");
            System.out.printf("      %-8s%n", sequence);
            System.out.println("                ");
            System.out.println("+----------------+");

            System.out.print("\nType Here: ");

            long start = System.currentTimeMillis();
            String answer = type.nextLine();
            long end = System.currentTimeMillis();

            double fastType = getFastTime(start, end);
            times.add(fastType);

            if (checkInput(sequence, answer)){
                System.out.println("\nLevel Complete!");
                score += 100;
                
            }else{
                System.out.println("\nIncorrect answer.");
            }
            
            System.out.printf("Typing time: %.2f seconds%n", fastType);
            System.out.println("Score: " + score);

            System.out.println("\nPress ENTER to continue.");
            type.nextLine();
        }

        finishGame(times);
    }
    
//  static void levelIntro(){
//
//        blankScreen();
//
//        System.out.println("\n\n");
//        System.out.println("\tPRESS ENTER TO CONTINUE");
//
//        //when pressing Enter
//        getInput();
//        blankScreen();
//
//        System.out.println("\n");
//        System.out.println("\tLEVEL " + lvl);
//        System.out.println();
//    }

    public void showLevel(int lvl){
        System.out.println("\n>>> LEVEL " + lvl + " <<<");
    }

    public String generateSequence(){
        String keys = "ASWD";
        String sequence = "";

        for (int i = 0; i < 4; i++){
            int position = rng.nextInt(keys.length());
            sequence += keys.charAt(position);
        }
        return sequence;
    }

    public boolean checkInput(String sequence, String answer){
        return sequence.equalsIgnoreCase(answer);
    }

    public double getFastTime(long start, long end){
        return (end - start) / 1000.0;
    }

    public void displayResult(ArrayList<Double> times){
        double total = 0;

        System.out.println("\n>>> GAME RESULTS <<<");

        for (int i = 0; i < times.size(); i++){
            System.out.printf("Level %d: %.2f seconds%n", i + 1, times.get(i));
            total += times.get(i);
        }
        System.out.printf("\nAverage Typed Time: %.2f seconds%n", total / times.size());
    }

//    public void finishGame(ArrayList<Double> times){
//        System.out.println("\n>>> GAME COMPLETED <<<");
//        displayResult(times);
//
//        System.out.println("\nThanks for playing!");
//    }
    
    public void finishGame(ArrayList<Double> times){
        System.out.println("\n>>> Game Complete <<<");
        displayResult(times);

        if (score > highScore) {
            highScore = score;
            saveHighScore();

            System.out.println("\nNEW HIGH SCORE!");
        }
        System.out.println("High Score: " + highScore);

        System.out.println("\nPress ENTER to return to menu.");
        type.nextLine();
    }
    
    public void displayHighScore(){
        
        try{
            File file = new File("rhythm_score.txt");
            
            if (file.exists()){
                Scanner fileScan = new Scanner(file);

                if (fileScan.hasNextInt()){
                    highScore = fileScan.nextInt();
                }
                fileScan.close();
            }

        }catch (FileNotFoundException e){
            highScore = 0;
        }
    }
    
    public void saveHighScore(){
        
        try{
            PrintWriter writer = new PrintWriter("rhythm_score.txt");

            writer.println(highScore);
            writer.close();

        }catch (FileNotFoundException e){
            System.out.println("Unable to save high score.");
        }
    }


    public static void main(String[] args){
        
        SecondRhythm game = new SecondRhythm();
        game.menu();
    }
}
