import java.util.Scanner;

/**
 * A class to make human player that implements Player interface.
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class HumanPlayer implements Player{
    //input socket
    private Scanner scanner;

    /**
     * Create a new HumanPlayer with a given input.
     *
     * @param scanner input socket.
     */
    public HumanPlayer(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * @inheritDoc
     *
     * This method implement put method of Player interface for human player.
     */
    public String put(int turn) {
        if (turn % 2 == 0) {
                System.out.println("White turn: put your bead. (for example: 2 7)");
                String bNum = scanner.next().trim();
                String  cNum = scanner.next().trim();
                return bNum + " " + cNum;
            }
       else {
            System.out.println("Black turn: put your bead. (for example: 2 7)");
            String bNum = scanner.next().trim();
            String cNum = scanner.next().trim();
            return bNum + " " + cNum;
        }
    }

    /**
     * @inheritDoc
     *
     * This method implement rotate method of Player interface for human player.
     */
    public String rotate(int turn) {
        if (turn % 2 == 0) {
            System.out.println("White turn: rotate a block. (for example: 4 A)");
            String bNumForR = scanner.next().trim();
            String direction = scanner.next().trim();
            return bNumForR + " " + direction;
        }
        else {
            System.out.println("Black turn: rotate a block. (for example: 4 A)");
            String bNumForR = scanner.next().trim();
            String direction = scanner.next().trim();
            return bNumForR + " " + direction;
        }
    }
}