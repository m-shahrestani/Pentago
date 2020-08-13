import java.util.Scanner;

/**
 * A class to drive program.
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //take game mode
        int mode;
        while (true) {
            System.out.println("Enter mode:");
            System.out.println("1- two player.");
            System.out.println("2- human with PC(black).");
            System.out.println("3- human with PC(white).");
            System.out.println("4- PC with PC.");
            mode = scanner.nextInt();
            if( mode == 1 || mode == 2 || mode == 3 || mode == 4) {
                break;
            }
            System.out.println("Invalid input.");
        }
        //make Pentago game
        GameManager play = new GameManager(mode ,scanner);
        play.instruction();
        play.startGame();
    }
}