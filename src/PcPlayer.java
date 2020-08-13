import java.util.Random;

/**
 * A class to make PC player that implements Player interface.
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class PcPlayer implements Player{
    //Board
    private Board board;

    /**
     * Create a new PcPlayer with a given board.
     *
     * @param board board of game.
     */
    public PcPlayer(Board board) {
        this.board = board;
        //test first PC last movement
//        board.getBlock(1).setBlack(1);
//        board.getBlock(1).setBlack(2);
//        board.getBlock(1).setBlack(3);
//        board.getBlock(2).setBlack(7);
        //test second PC last movement
//        board.getBlock(1).setWhite(1);
//        board.getBlock(1).setWhite(2);
//        board.getBlock(1).setWhite(3);
//        board.getBlock(2).setWhite(7);
    }

    /**
     * @inheritDoc
     *
     * This method implement put method of Player interface for PC player.
     */
    public String put(int turn) {
        if(turn % 2 == 0) {
            System.out.println("White turn: put your bead. (for example: 2 7)");
        }
        else {
            System.out.println("Black turn: put your bead. (for example: 2 7)");
        }
        String input = decidePut(turn);
        int bNum = (input.charAt(0)) - '0';
        int cNum = (input.charAt(2)) - '0';
        System.out.println(bNum + " " + cNum);
        return bNum + " " + cNum;
    }

    /**
     * @inheritDoc
     *
     * This method implement rotate method of Player interface for PC player.
     */
    public String rotate(int turn) {
        if(turn % 2 == 0) {
            System.out.println("White turn: rotate a block. (for example: 4 A)");
        }
        else {
            System.out.println("Black turn: rotate a block. (for example: 4 A)");
        }
        String input = decideRotate(turn);
        int bNumForR = (input.charAt(0)) - '0';
        char direction = (input.charAt(2));
        System.out.println(bNumForR + " " + direction);
        return bNumForR + " " + direction;
    }

    /**
     * decide to do best putting.
     *
     * @param turn the turn of game.
     * @return a String of (bNum + " " + cNum).
     */
    private String decidePut(int turn) {
        int bNum;
        int cNum;
            if (lastMovement(turn) != null) {
                String input = lastMovement(turn);
                bNum = (input.charAt(0)) - '0';
                cNum = (input.charAt(2)) - '0';
            }
           else if(lastMovement(turn) == null && getMaxEnemyScore(turn) >= 2) {
                String input = convert(turn);
                bNum = (input.charAt(0)) - '0';
                cNum = (input.charAt(2)) - '0';
            }
            else {
                do {
                    bNum = (new Random().nextInt(4)) + 1;
                    cNum = (new Random().nextInt(9)) + 1;
                } while (board.getBlock(bNum).getCell(cNum) != '◌');
            }
        return bNum + " " + cNum;
    }

    /**
     * decide to do best rotation.
     * @param turn the turn of game.
     * @return a String of (bNumForR + " " + direction).
     */
    private String decideRotate(int turn) {
        int bNumForR;
        char direction;
        if (lastMovement(turn) != null) {
            String input = lastMovement(turn);
            bNumForR = (input.charAt(4)) - '0';
            direction = input.charAt(6);
        }
        else {
            bNumForR = (new Random().nextInt(4)) + 1;
            direction = ((new Random().nextInt(2)) + 1) == 0 ? 'A' : 'C';
        }
        return bNumForR + " " + direction;
    }

    /**
     * give last movement.
     * @param turn the turn of game.
     * @return a String of (bNum + " " + cNum).
     */
    private String lastMovement(int turn) {
        int current = ((turn % 2 == 0)?2:1);
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 9; j++) {
                for (int k = 1; k <= 4; k++) {
                    for (int m = 0; m < 2; m++) {
                        //backup board
                        Board think = new Board();
                        for (int n = 1; n <= 4; n++) {
                            for (int q = 1; q <= 9; q++) {
                                if (board.getBlock(n).getCell(q) == 'W') {
                                    think.getBlock(n).setWhite(q);
                                }
                                if (board.getBlock(n).getCell(q) == 'B') {
                                    think.getBlock(n).setBlack(q);
                                }
                                if (board.getBlock(n).getCell(q) == '◌') {
                                    think.getBlock(n).setEmpty(q);
                                }
                            }
                        }

                        //do
                        if (think.getBlock(i).getCell(j) == '◌') {
                            think.put(i, j, turn);
                            if (m == 0) {
                                think.rotate(k, 'C');
                                if (think.isFinish() == current) {
                                    return i + " " + j + " " + k + " " + 'C';
                                }
                            } else {
                                think.rotate(k, 'A');
                                if (think.isFinish() == current) {
                                    return i + " " + j + " " + k + " " + 'A';
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * give defend movement.
     * @param turn the turn of game.
     * @return a String of (posX + " " + posY).
     */
    private String defendMovement(int turn) {
        int current = ((turn % 2 == 0)?2:1);
        int enemy = ((turn % 2 == 0)?1:2);

        //copy to cells collection
        int[][] cells = new int[6][6];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                cells[i][j] = 0;
            }
        }
        int temp = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getBlock(1).getCell(temp) == 'B') {
                    cells[i][j] = 1;
                }
                if (board.getBlock(1).getCell(temp) == 'W') {
                    cells[i][j] = 2;
                }
                temp++;
            }
        }
        temp = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 3; j < 6; j++) {
                if (board.getBlock(2).getCell(temp) == 'B') {
                    cells[i][j] = 1;
                }
                if (board.getBlock(2).getCell(temp) == 'W') {
                    cells[i][j] = 2;
                }
                temp++;
            }
        }
        temp = 1;
        for (int i = 3; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getBlock(3).getCell(temp) == 'B') {
                    cells[i][j] = 1;
                }
                if (board.getBlock(3).getCell(temp) == 'W') {
                    cells[i][j] = 2;
                }
                temp++;
            }
        }
        temp = 1;
        for (int i = 3; i < 6; i++) {
            for (int j = 3; j < 6; j++) {
                if (board.getBlock(4).getCell(temp) == 'B') {
                    cells[i][j] = 1;
                }
                if (board.getBlock(4).getCell(temp) == 'W') {
                    cells[i][j] = 2;
                }
                temp++;
            }
        }

        for (int i = 0; i < 6; i++) {
            try {
                for (int j = 0; j < 6; j++) {
                    int status = checkTogether(cells, i, j, current, enemy);
                    if (status == 1 && cells[i + 1][j - 1] == 0) {
                        return (i + 1) + " " + (j - 1);
                    }
                    if (status == 2 && cells[i - 1][j + 1] == 0) {
                        return (i - 1) + " " + (j + 1);
                    }
                    if (status == 3 && cells[i - 1][j - 1] == 0) {
                        return (i - 1) + " " + (j - 1);
                    }
                    if (status == 4 && cells[i + 1][j + 1] == 0) {
                        return (i + 1) + " " + (j + 1);
                    }
                    if (status == 5 && cells[i + 1][j] == 0) {
                        return (i + 1) + " " + (j);
                    }
                    if (status == 6 && cells[i - 1][j] == 0) {
                        return (i - 1) + " " + (j);
                    }
                    if (status == 7 && cells[i][j - 1] == 0) {
                        return (i) + " " + (j - 1);
                    }
                    if (status == 8 && cells[i][j + 1] == 0) {
                        return (i) + " " + (j + 1);
                    }
                }
            }catch (IndexOutOfBoundsException ignored) {}
        }
        return null;
    }

    /**
     * give convert movement.
     * @param turn the turn of game.
     * @return a String of (bNum + " " + cNum).
     */
    private String convert(int turn) {
        String s = defendMovement(turn);
        int x = s.charAt(0) - '0';
        int y = s.charAt(2) - '0';
        int bNum = 0;
        int cNum = 0;
        if(0 <= x  && x < 3 && 0 <= y && y < 3) {
            bNum = 1;
            cNum = ((y * 3) + 1) + x;
        }
        if(0 <= x  && x < 3 && 3 <= y && y < 6) {
            bNum = 2;
            cNum = (((y - 3) * 3) + 1) + x;
        }
        if(3 <= x  && x < 6 && 0 <= y && y < 3) {
            bNum = 3;
            cNum = ((y * 3) + 1) + (x-3);
        }
        if(3 <= x  && x < 6 && 3 <= y && y < 6) {
            bNum = 4;
            cNum = (((y - 3) * 3) + 1) + (x - 3);
        }
        else {
            return null;
        }
        return bNum + " " + cNum;
    }

    /**
     * give max score of enemy.
     * @param turn the turn of game.
     * @return score.
     */
    private int getMaxEnemyScore(int turn) {
        //copy to cells collection
        int[][] cells = new int[6][6];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                cells[i][j] = 0;
            }
        }
        int temp = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getBlock(1).getCell(temp) == 'B') {
                    cells[i][j] = 1;
                }
                if (board.getBlock(1).getCell(temp) == 'W') {
                    cells[i][j] = 2;
                }
                temp++;
            }
        }
        temp = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 3; j < 6; j++) {
                if (board.getBlock(2).getCell(temp) == 'B') {
                    cells[i][j] = 1;
                }
                if (board.getBlock(2).getCell(temp) == 'W') {
                    cells[i][j] = 2;
                }
                temp++;
            }
        }
        temp = 1;
        for (int i = 3; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getBlock(3).getCell(temp) == 'B') {
                    cells[i][j] = 1;
                }
                if (board.getBlock(3).getCell(temp) == 'W') {
                    cells[i][j] = 2;
                }
                temp++;
            }
        }
        temp = 1;
        for (int i = 3; i < 6; i++) {
            for (int j = 3; j < 6; j++) {
                if (board.getBlock(4).getCell(temp) == 'B') {
                    cells[i][j] = 1;
                }
                if (board.getBlock(4).getCell(temp) == 'W') {
                    cells[i][j] = 2;
                }
                temp++;
            }
        }

        //check cells
        int bCount = 0;
        int wCount = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if(cells[i][j] == 1) {
                    if(board.checkCellSet(cells ,i ,j ,1,2)) {
                        bCount++;
                    }
                }
                if(cells[i][j] == 2) {
                    if(board.checkCellSet(cells ,i ,j ,2,1)) {
                        wCount++;
                    }
                }
            }
        }
        if (turn % 2 == 0) {
            return bCount;
        }
        else {
            return wCount;
        }
    }

    /**
     * Determine condition of cell with given positions and current, enemy.
     * @param row x position.
     * @param column y position.
     * @param current The colorId of current player.
     * @param enemy The colorId of enemy player.
     */
    private int checkTogether(int[][] cells, int row, int column, int current, int enemy) {
        int i, j = column, count = 1;
        //upper-right direction
        for (i = row; i > 0; i--) {
            try {
                if (cells[i - 1][j + 1] == current) {
                    count++;
                    if ( (j + 2) < 6 && (i - 2) >= 0 && cells[i - 2][j + 2] == enemy) {
                        break;
                    }
                } else
                    break;
                j++;
            }catch (IndexOutOfBoundsException ignored) {}
        }
        if(count >= 2) {
            return 1;
        }

        //lower-left direction
        count = 1;
        j = column;
        for (i = row; i < 6; i++) {
            try {
                if (cells[i + 1][j - 1] == current) {
                    count++;
                    if ((j - 2) >= 0 && (i + 2) < 6 && cells[i + 2][j - 2] == enemy) {
                        break;
                    }
                } else
                    break;
                j--;
            }catch (IndexOutOfBoundsException ignored) {}
        }
        if(count >= 2) {
            return 2;
        }

        //lower-right direction
        count = 1;
        j = column;
        for (i = row; i < 6; i++) {
            try {
                if (cells[i + 1][j + 1] == current) {
                    count++;
                    if ((j + 2) < 6 && (i + 2) < 6 && cells[i + 2][j + 2] == enemy) {
                        break;
                    }
                } else
                    break;
                j++;
            }catch (IndexOutOfBoundsException ignored) {}
        }
        if(count >= 2) {
            return 3;
        }

        //upper-left direction
        count = 1;
        for (i = row; i > 0; i--) {
            try {
                if (cells[i - 1][j - 1] == current) {
                    count++;
                    if ((j - 2) >= 0 && (i - 2) >= 0 && cells[i - 2][j - 2] ==enemy) {
                        break;
                    }
                } else
                    break;
                j--;
            }catch (IndexOutOfBoundsException ignored) {}
        }
        if(count >= 2) {
            return 4;
        }

        //upwards
        count = 1;
        for (i = row; i > 0; i--) {
            try {
                if (cells[i - 1][column] == current) {
                    count++;
                    if ((i - 2) >= 0 && cells[i - 2][column] == enemy) {
                        break;
                    }
                } else
                    break;
            }catch (IndexOutOfBoundsException ignored) {}
        }
        if(count >= 2) {
            return 5;
        }

        //downwards
        count = 1;
        for (i = row; i < 6; i++) {
            try {
                if (cells[i + 1][column] == current) {
                    count++;
                    if ((i + 2) < 6 && cells[i + 2][column] == enemy) {
                        break;
                    }
                } else
                    break;
            }catch (IndexOutOfBoundsException ignored) {}
        }
        if(count >= 2) {
            return 6;
        }

        //rightwards
        count = 1;
        for (i = column; i < 6; i++) {
            try {
                if (cells[row][i + 1] == current) {
                    count++;
                    if ((i + 2) < 6 && cells[row][i + 2] == enemy) {
                        break;
                    }
                } else
                    break;
            }catch (IndexOutOfBoundsException ignored) {}
        }
        if(count >= 2) {
            return 7;
        }

        //leftwards
        count = 1;
        for (j = column; j > 0; j--) {
            try {
                if (cells[row][j - 1] == current) {
                    count++;
                    if ((j - 2) >= 0 && cells[row][j - 2] == enemy) {
                        break;
                    }
                } else
                    break;
            }catch (IndexOutOfBoundsException ignored) {}
        }
        if (count >= 3) {
            return 8;
        }
        return 0;
    }
}
