import edu.princeton.cs.algs4.In;
import org.junit.Assert;
import org.junit.Test;

import java.util.Scanner;

/**
 * Created by trash on 07-Feb-17.
 */
public class BoggleSolverTest {

    public static final String PATH = "src/test/resources/";

    @Test
    public void testBoggleSolver_Board4x4() {

        In in = new In(new Scanner(BoggleSolverTest.class.getClassLoader().getResourceAsStream("dictionary-algs4.txt")));
        String[] dictionary = in.readAllLines();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard(PATH + "board4x4.txt");
        int score = 0;
        for (String word : solver.getAllValidWords(board)) {
            score += solver.scoreOf(word);
        }
        Assert.assertEquals(33, score);

    }

    @Test
    public void testBoggleSolver_BoardQ() {

        In in = new In(new Scanner(BoggleSolverTest.class.getClassLoader().getResourceAsStream("dictionary-algs4.txt")));
        String[] dictionary = in.readAllLines();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard(PATH + "board-q.txt");
        int score = 0;
        for (String word : solver.getAllValidWords(board)) {
            score += solver.scoreOf(word);
        }
        Assert.assertEquals(84, score);

    }

}