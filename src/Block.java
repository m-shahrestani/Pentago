/**
 * A class to hold details of a block.
 *
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class Block {
    //an Array to hold cells
    private char[][] cells;

    /**
     * Create a new Block.
     * Initialize cells with '◌'.
     *
     */
    public Block() {
        cells = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++ ) {
                cells[i][j] = '◌';
            }
        }
    }

    /**
     * get The cell with given number.
     * @return cell.
     */
    public char getCell(int n) {
        switch (n) {
            case 1:
                return cells[0][0];
            case 2:
                return cells[1][0];
            case 3:
                return cells[2][0];
            case 4:
                return cells[0][1];
            case 5:
                return cells[1][1];
            case 6:
                return cells[2][1];
            case 7:
                return cells[0][2];
            case 8:
                return cells[1][2];
            case 9:
                return cells[2][2];
        }
        return ' ';
    }

    /**
     * set white bead into given cell number.
     * @param n number of cell.
     */
    public void setWhite(int n) {
        //        char ch = '⚫';
        char ch = 'W';
        switch (n) {
            case 1:
                cells[0][0] = ch;
                break;
            case 2:
                cells[1][0] = ch;
                break;
            case 3:
                cells[2][0] = ch;
                break;
            case 4:
                cells[0][1] = ch;
                break;
            case 5:
                cells[1][1] = ch;
                break;
            case 6:
                cells[2][1] = ch;
                break;
            case 7:
                cells[0][2] = ch;
                break;
            case 8:
                cells[1][2] = ch;
                break;
            case 9:
                cells[2][2] = ch;
                break;
        }
    }

    /**
     * set black bead into given cell number.
     * @param n number of cell.
     */
    public void setBlack(int n) {
//        char ch = '⚪';
        char ch = 'B';
        switch (n) {
            case 1:
                cells[0][0] = ch;
                break;
            case 2:
                cells[1][0] = ch;
                break;
            case 3:
                cells[2][0] = ch;
                break;
            case 4:
                cells[0][1] = ch;
                break;
            case 5:
                cells[1][1] = ch;
                break;
            case 6:
                cells[2][1] = ch;
                break;
            case 7:
                cells[0][2] = ch;
                break;
            case 8:
                cells[1][2] = ch;
                break;
            case 9:
                cells[2][2] = ch;
                break;
        }
    }

    /**
     * set empty into given cell number.
     * @param n number of cell.
     */
    public void setEmpty(int n) {
        char ch = '◌';
        switch (n) {
            case 1:
                cells[0][0] = ch;
                break;
            case 2:
                cells[1][0] = ch;
                break;
            case 3:
                cells[2][0] = ch;
                break;
            case 4:
                cells[0][1] = ch;
                break;
            case 5:
                cells[1][1] = ch;
                break;
            case 6:
                cells[2][1] = ch;
                break;
            case 7:
                cells[0][2] = ch;
                break;
            case 8:
                cells[1][2] = ch;
                break;
            case 9:
                cells[2][2] = ch;
                break;
        }
    }

    /**
     * rotate the block clockwise.
     */
    public void rotateClockwise() {
        char[][] temp = new char[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(cells[i], 0, temp[i], 0, 3);
        }

        cells[0][0] = temp[0][2];
        cells[0][1] = temp[1][2];
        cells[0][2] = temp[2][2];
        cells[1][0] = temp[0][1];

        cells[1][2] = temp[2][1];
        cells[2][0] = temp[0][0];
        cells[2][1] = temp[1][0];
        cells[2][2] = temp[2][0];
    }

    /**
     * rotate the block anticlockwise.
     */
    public void rotateAntiClockwise() {
        char[][] temp = new char[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(cells[i], 0, temp[i], 0, 3);
        }

        cells[0][2] = temp[0][0];
        cells[1][2] = temp[0][1];
        cells[2][2] = temp[0][2];
        cells[0][1] = temp[1][0];

        cells[2][1] = temp[1][2];
        cells[0][0] = temp[2][0];
        cells[1][0] = temp[2][1];
        cells[2][0] = temp[2][2];
    }
}
