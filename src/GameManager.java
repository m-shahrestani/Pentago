import java.util.Scanner;

/**
 * A class to manage Pentago game.
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class GameManager {
    //board game.
    private Board board;
    //mode of game 1 for two players and 2,3 for with PC and 4 for two PC players.
    private int mode;
    //the status of game for continue.
    private boolean gameStatus;
    //human player
    private Player human;
    //PC player
    private Player pc;

    /**
     * Create a new GameManager with given mode and input.
     *
     * @param mode mode of game.
     * @param scanner input socket.
     */
    public GameManager(int mode ,Scanner scanner) {
        board = new Board();
        this.mode = mode;
        gameStatus = true;
        if (mode == 1) {
            human = new HumanPlayer(scanner);
        }
        if (mode == 2 || mode == 3) {
            human = new HumanPlayer(scanner);
            pc = new PcPlayer(board);
        }
        if (mode == 4) {
            pc = new PcPlayer(board);
        }
    }

    /**
     * Show instruction of The game.
     */
    public void instruction() {
        System.out.println("Instruction:");
        System.out.println("Enter number of block + number of selected cell + number of block to rotate + A or C for Anticlockwise ↺ or Clockwise ↻ direction.");
        System.out.println("+----1----+----2----+");
        System.out.println("| " + 1 + "  " + 2 + "  " + 3 + " | " + 1 + "  " + 2 + "  " + 3 + " | ");
        System.out.println("| " + 4 + "  " + 5 + "  " + 6 + " | " + 4 + "  " + 5 + "  " + 6 + " | ");
        System.out.println("| " + 7 + "  " + 8 + "  " + 9 + " | " + 7 + "  " + 8 + "  " + 9 + " | ");
        System.out.println("+----3----+----4----+");
        System.out.println("| " + 1 + "  " + 2 + "  " + 3 + " | " + 1 + "  " + 2 + "  " + 3 + " | ");
        System.out.println("| " + 4 + "  " + 5 + "  " + 6 + " | " + 4 + "  " + 5 + "  " + 6 + " | ");
        System.out.println("| " + 7 + "  " + 8 + "  " + 9 + " | " + 7 + "  " + 8 + "  " + 9 + " | ");
        System.out.println("+---------+---------+");
        System.out.println(".......................................................................................................................");
    }

    /**
     * Start game.
     */
    public void startGame() {
        int turn = 0;
        show();

        //human-VS-human
        if (mode == 1) {
            while (gameStatus) {
                while (gameStatus) {
                    String input = human.put(turn);
                    int bNum = (input.charAt(0)) - '0';
                    int cNum = (input.charAt(2)) - '0';
                    if (board.validPut(bNum, cNum)) {
                        put(bNum, cNum, turn);
                        show();
                        isFinish();
                        break;
                    }

                }
                while (gameStatus) {
                    String input = human.rotate(turn);
                    int bNumForR = (input.charAt(0)) - '0';
                    char direction = input.charAt(2);
                    if (board.validRotate(bNumForR, direction)) {
                        rotate(bNumForR, direction);
                        show();
                        isFinish();
                        turn++;
                        break;
                    }
                }
            }
        }

        //human-VS-PC
        if (mode == 2) {
            while (gameStatus) {
                if (turn % 2 == 0) {
                    while (gameStatus) {
                        String input = human.put(turn);
                        int bNum = (input.charAt(0)) - '0';
                        int cNum = (input.charAt(2)) - '0';
                        if (board.validPut(bNum, cNum)) {
                            put(bNum, cNum, turn);
                            show();
                            isFinish();
                            break;
                        }
                    }
                    while (gameStatus) {
                        String input = human.rotate(turn);
                        int bNumForR = (input.charAt(0)) - '0';
                        char direction = input.charAt(2);
                        if (board.validRotate(bNumForR, direction)) {
                            rotate(bNumForR, direction);
                            show();
                            isFinish();
                            turn++;
                            break;
                        }
                    }
                } else {
                    if (gameStatus) {
                        String input = pc.put(turn);
                        int bNum = (input.charAt(0)) - '0';
                        int cNum = (input.charAt(2)) - '0';
                        put(bNum, cNum, turn);
                        show();
                        isFinish();
                    }
                    if (gameStatus) {
                        String input = pc.rotate(turn);
                        int bNumForR = (input.charAt(0)) - '0';
                        char direction = input.charAt(2);
                        rotate(bNumForR, direction);
                        show();
                        isFinish();
                        turn++;
                    }
                }
            }
        }

        //PC-VS-human
        if (mode == 3) {
            while (gameStatus) {
                if (turn % 2 == 0) {
                    if (gameStatus) {
                        String input = pc.put(turn);
                        int bNum = (input.charAt(0)) - '0';
                        int cNum = (input.charAt(2)) - '0';
                        put(bNum, cNum, turn);
                        show();
                        isFinish();
                    }
                    if (gameStatus) {
                        String input = pc.rotate(turn);
                        int bNumForR = (input.charAt(0)) - '0';
                        char direction = input.charAt(2);
                        rotate(bNumForR, direction);
                        show();
                        isFinish();
                        turn++;
                    }
                }
                else {
                    while (gameStatus) {
                        String input = human.put(turn);
                        int bNum = (input.charAt(0)) - '0';
                        int cNum = (input.charAt(2)) - '0';
                        if (board.validPut(bNum, cNum)) {
                            put(bNum, cNum, turn);
                            show();
                            isFinish();
                            break;
                        }
                    }
                    while (gameStatus) {
                        String input = human.rotate(turn);
                        int bNumForR = (input.charAt(0)) - '0';
                        char direction = input.charAt(2);
                        if (board.validRotate(bNumForR, direction)) {
                            rotate(bNumForR, direction);
                            show();
                            isFinish();
                            turn++;
                            break;
                        }
                    }
                }
            }
        }

        //PC-VS-PC
        if (mode == 4) {
            while (gameStatus) {
                if (gameStatus) {
                    String input = pc.put(turn);
                    int bNum = (input.charAt(0)) - '0';
                    int cNum = (input.charAt(2)) - '0';
                    put(bNum, cNum, turn);
                    show();
                    isFinish();
                }
                if (gameStatus) {
                    String input = pc.rotate(turn);
                    int bNumForR = (input.charAt(0)) - '0';
                    char direction = input.charAt(2);
                    rotate(bNumForR, direction);
                    show();
                    isFinish();
                    turn++;
                }
            }
        }
    }

    /**
     * get The status of game.
     * @return gameStatus.
     */
    private void setGameStatus() {
        this.gameStatus = false;
    }

    /**
     * Show the board.
     */
    private void show() {
        board.printBoard();
    }

    /**
     * Call put method in board with given block number and cell number and turn.
     * @param bNum block number.
     * @param cNum cell number.
     * @param turn turn of player.
     */
    private void put(int bNum, int cNum, int turn) {
        board.put(bNum, cNum, turn);
    }

    /**
     * Call rotate method in board with given block number and direction.
     * @param bNumForR block number for rotate.
     * @param direction direction.
     */
    private void rotate(int bNumForR, char direction) {
        board.rotate(bNumForR, direction);
    }

    /**
     * Determine status of game finishing.
     * if game was finished, set gameStatus false.
     */
    private void isFinish() {
        if (board.isFinish() == 1) {
            System.out.println("Black won.");
            setGameStatus();
        }
        if (board.isFinish() == 2) {
            System.out.println("White won.");
            setGameStatus();
        }
        if (board.isFinish() == 3) {
            System.out.println("Draw.");
            setGameStatus();
        }
    }
}