/**
 * A class to make game board.
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class Board {
    //an Array to hold blocks
    private Block[] blocks;

    /**
     * Create a new Board.
     *
     */
    public Board() {
        blocks = new Block[4];
        for (int i = 0; i < 4; i++) {
            blocks[i] = new Block();
        }
    }

    /**
     * get The block with given number.
     * @param number number of block.
     * @return block.
     */
    public Block getBlock(int number) {
        return blocks[number-1];
    }

    /**
     * Show the board into terminal.
     */
    public void printBoard() {
        System.out.println("+---------+---------+");
        System.out.println("| " + blocks[0].getCell(1) + "  " +blocks[0].getCell(2) + "  " + blocks[0].getCell(3) + " | " + blocks[1].getCell(1) + "  " + blocks[1].getCell(2) + "  " + blocks[1].getCell(3) + " | ");
        System.out.println("| " + blocks[0].getCell(4) + "  " +blocks[0].getCell(5) + "  " + blocks[0].getCell(6) + " | " + blocks[1].getCell(4) + "  " + blocks[1].getCell(5) + "  " + blocks[1].getCell(6) + " | ");
        System.out.println("| " + blocks[0].getCell(7) + "  " +blocks[0].getCell(8) + "  " + blocks[0].getCell(9) + " | " + blocks[1].getCell(7) + "  " + blocks[1].getCell(8) + "  " + blocks[1].getCell(9) + " | ");
        System.out.println("+---------+---------+");
        System.out.println("| " + blocks[2].getCell(1) + "  " +blocks[2].getCell(2) + "  " + blocks[2].getCell(3) + " | " + blocks[3].getCell(1) + "  " + blocks[3].getCell(2) + "  " + blocks[3].getCell(3) + " | ");
        System.out.println("| " + blocks[2].getCell(4) + "  " +blocks[2].getCell(5) + "  " + blocks[2].getCell(6) + " | " + blocks[3].getCell(4) + "  " + blocks[3].getCell(5) + "  " + blocks[3].getCell(6) + " | ");
        System.out.println("| " + blocks[2].getCell(7) + "  " +blocks[2].getCell(8) + "  " + blocks[2].getCell(9) + " | " + blocks[3].getCell(7) + "  " + blocks[3].getCell(8) + "  " + blocks[3].getCell(9) + " | ");
        System.out.println("+---------+---------+");
    }

    /**
     * Determine validity of given block number and cell number for put.
     * @param bNum block number.
     * @param cNum cell number.
     * @return true if the choice is valid for put, false otherwise.
     */
    public boolean validPut(int bNum, int cNum) {
        if (!(1 <=bNum && bNum <= 4)) {
            System.out.println("Invalid block number.");
            return false;
        }
        if (!(1 <=cNum && cNum <= 9)) {
            System.out.println("Invalid cell number.");
            return false;
        }
        if (!(this.getBlock(bNum).getCell(cNum) == '◌')) {
            System.out.println("Invalid choice.");
            return false;
        }
        return true;
    }

    /**
     * Determine validity of given block number and direction for rotate.
     * @param bNumForR block number for rotate.
     * @param direction direction.
     * @return true if the choice is valid for put, false otherwise.
     */
    public boolean validRotate(int bNumForR, char direction) {
        if (!(1 <= bNumForR && bNumForR <= 4)) {
            System.out.println("Invalid block number for rotate.");
            return false;
        }
        if (!(direction == 'C' || direction == 'A') ) {
            System.out.println("Invalid character for direction.");
            return false;
        }
        return true;
    }

    /**
     * Put with given block number and cell number and turn.
     * @param bNum block number.
     * @param cNum cell number.
     * @param turn turn of player.
     */
    public void put(int bNum, int cNum, int turn) {
        if (turn % 2 == 0) {
            this.getBlock(bNum).setWhite(cNum);
        }
        if (turn % 2 != 0) {
            this.getBlock(bNum).setBlack(cNum);
        }
    }

    /**
     * Rotate with given block number and direction.
     * @param bNumForR block number for rotate.
     * @param direction direction.
     */
    public void rotate(int bNumForR, char direction) {
        if (direction == 'C') {
            this.getBlock(bNumForR).rotateClockwise();
        }
        if (direction == 'A') {
            this.getBlock(bNumForR).rotateAntiClockwise();
        }
    }

    /**
     * Determine status of game and winner.
     * @return 0 or 1 or 2 or 3.
     * if game continues return 0.
     * if black was winner return 1.
     * if white was winner return 2.
     * if board was full  or draw return 3.
     */
    public int isFinish() {
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
                if (this.getBlock(1).getCell(temp) == 'B') {
                    cells[i][j] = 1;
                }
                if (this.getBlock(1).getCell(temp) == 'W') {
                    cells[i][j] = 2;
                }
                temp++;
            }
        }
        temp = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 3; j < 6; j++) {
                if (this.getBlock(2).getCell(temp) == 'B') {
                    cells[i][j] = 1;
                }
                if (this.getBlock(2).getCell(temp) == 'W') {
                    cells[i][j] = 2;
                }
                temp++;
            }
        }
        temp = 1;
        for (int i = 3; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.getBlock(3).getCell(temp) == 'B') {
                    cells[i][j] = 1;
                }
                if (this.getBlock(3).getCell(temp) == 'W') {
                    cells[i][j] = 2;
                }
                temp++;
            }
        }
        temp = 1;
        for (int i = 3; i < 6; i++) {
            for (int j = 3; j < 6; j++) {
                if (this.getBlock(4).getCell(temp) == 'B') {
                    cells[i][j] = 1;
                }
                if (this.getBlock(4).getCell(temp) == 'W') {
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
                    if(checkCellSet(cells ,i ,j ,1,2)) {
                        bCount++;
                    }
                }
                if(cells[i][j] == 2) {
                    if(checkCellSet(cells ,i ,j ,2,1)) {
                        wCount++;
                    }
                }
            }
        }

        //check winner
        if(wCount < 5 && bCount >= 5) {
            return 1;
        }
        if(wCount >= 5 && bCount < 5) {
            return 2;
        }
        if(wCount >= 5) {
            return 3;
        }
        //check end
        boolean flag = false;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if(cells[i][j] == 0) {
                    flag = true;
                    break;
                }
            }
        }
        if (!flag) {
            return 3;
        }
        return 0;
    }

    /**
     * Determine condition of cell with given positions and current, enemy.
     * @param row x position.
     * @param column y position.
     * @param current The colorId of current player.
     * @param enemy The colorId of enemy player.
     */
    public boolean checkCellSet(int[][] cells, int row, int column, int current, int enemy) {
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
        //lower-left direction
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
        if(count >= 5) {
            return true;
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
        //upper-left direction
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
        if(count >= 5) {
            return true;
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
        //downwards
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
        if(count >= 5) {
            return true;
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
        //leftwards
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
        return count >= 5;
    }
}
