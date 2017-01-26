import edu.princeton.cs.algs4.Picture;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Queue;

import static org.junit.Assert.assertEquals;

/**
 * Created by trash on 23-Jan-17.
 */
public class SeamCarverTest {
    private final static String PATH = "src/test/resources/";
    private SeamCarver carver;

    @Before
    public void init() {
        File file = new File(PATH + "4x6.png");
        carver = new SeamCarver(new Picture(file));
    }

    @Test(expected = NullPointerException.class)
    public void testSeamCarver_NullInput() {
        new SeamCarver(null);
    }

    @Test(expected = NullPointerException.class)
    public void testSeamCaver_NullInRemoveVertical() {
        carver.removeVerticalSeam(null);
    }

    @Test(expected = NullPointerException.class)
    public void testSeamCaver_NullInRemoveHorizontal() {
        carver.removeHorizontalSeam(null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBound() {
        carver.energy(8, 9);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOneVPixelRemove() {
        carver = new SeamCarver(new Picture(PATH + "1x1.png"));
        int[] seam = new int[]{1};
        carver.removeVerticalSeam(seam);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOneHPixelRemove() {
        carver = new SeamCarver(new Picture(PATH + "1x1.png"));
        int[] seam = new int[]{1};
        carver.removeHorizontalSeam(seam);
    }

    @Test
    public void testEnerge_VerticalSeam() {
        carver = new SeamCarver(new Picture(PATH + "6x5.png"));
        int[] actual = new int[]{};
        int[] expeted = new int[]{3, 4, 3, 2, 2};
        carver.removeVerticalSeam(actual);
        assertEquals(expeted, actual);

    }

    @Test
    public void testEnerge_HorizontalSeam() {
        carver = new SeamCarver(new Picture(PATH + "6x5.png"));
        int[] actual = new int[]{};
        int[] expeted = new int[]{2, 2, 1, 2, 1, 2};
        carver.removeHorizontalSeam(actual);
        assertEquals(expeted, actual);
    }

    @Test
    public void testEnerge_Simple() {
        carver = new SeamCarver(new Picture(PATH + "3x4.png"));
        assertEquals(Math.sqrt(52024d), carver.energy(1, 2), 0);
        assertEquals(Math.sqrt(52225d), carver.energy(1, 1), 0);
        assertEquals(1000, carver.energy(0, 0), 0);
        assertEquals(1000, carver.energy(0, 1), 0);
        assertEquals(1000, carver.energy(0, 2), 0);
        assertEquals(1000, carver.energy(0, 3), 0);
        assertEquals(1000, carver.energy(1, 0), 0);
        assertEquals(1000, carver.energy(1, 3), 0);
        assertEquals(1000, carver.energy(2, 0), 0);
        assertEquals(1000, carver.energy(2, 1), 0);
        assertEquals(1000, carver.energy(2, 2), 0);
        assertEquals(1000, carver.energy(2, 3), 0);
    }

    @Test
    public void testTopologicalOrder_3x4() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        carver = new SeamCarver(new Picture(PATH + "3x4.png"));
        Method buildHTopology = SeamCarver.class.getDeclaredMethod("buildVTopology", int.class);
        Object result = buildHTopology.invoke(carver, 1);
        Assert.assertTrue(result instanceof Queue);
        assertEquals(10, ((Queue<Integer>) result).size());
    }

    @Test
    public void testTopologicalOrder_6x5() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        carver = new SeamCarver(new Picture(PATH + "6x5.png"));
        Method buildHTopology = SeamCarver.class.getDeclaredMethod("buildVTopology", int.class);
        Object result = buildHTopology.invoke(carver, 2);
        Assert.assertTrue(result instanceof Queue);
        Queue<Integer> queue = (Queue<Integer>) result;
        assertEquals(21, queue.size());
        System.out.println(result.toString());
        for (int j = 0; j < carver.height(); j++) {
            for (int i = 0; i < carver.width(); i++) {
                int index = carver.width() * j + i;
                if (queue.contains(index)) {
                    System.out.print(index);
                } else {
                    if (index < 12) {
                        System.out.print("_|");
                    } else {
                        System.out.print("_");

                    }
                }
            }
            System.out.println();
        }

    }
}