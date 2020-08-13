/**
 * An interface for player.
 * The interface represents player methods for game.
 * Implementations of this interface: put and rotate input.
 *
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public interface Player {

    /**
     * This method give input for put method in GameManger with given turn.
     *
     * @param turn the turn of game.
     * @return a String of (bNum + " " + cNum).
     */
    String put(int turn);

    /**
     * This method give input for rotate method in GameManger with given turn.
     *
     * @param turn the turn of game.
     * @return a String of (bNumForR + " " + direction).
     */
    String rotate(int turn);
}
