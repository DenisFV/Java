import junit.framework.Assert;
import org.junit.*;

import java.io.*;
import java.math.BigDecimal;

import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.junit.runners.MethodSorters;

import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Mul2matrixTests extends Assert {

    @Rule
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();
    private Mul2matrix m;
    private static FileWriter fw;
    private static BigDecimal normVal[][];
    private static int numPotok = -1, step, size, start, val[];

    @Before
    public void before() {
        m = new Mul2matrix();
    }

    @After
    public void after() {
        m = null;
    }


    @Test
    public void testMet_1_check() {

        systemInMock.provideLines("0", "kssd", "-789", "1000000", "1000000", "100000000000", "", " ", "\n", "3", "5");
        int a[][] = m.check();
        assertEquals(a.length, new int[3][5].length);
        assertEquals(a[0].length, (new int[3][5])[0].length);

        systemInMock.provideLines("3", "ksdfsd", "-789", "100000000000", "", " ", "\n", "5");
        int b[][] = m.check();
        assertEquals(b.length, new int[3][5].length);
        assertEquals(b[0].length, (new int[3][5])[0].length);
    }

    @Test
    public void testMet_2_max() {

        assertEquals(m.max(new int[6][2], new int[2][6]).length, new int[2][2].length);
        assertEquals(m.max(new int[6][2], new int[2][6])[0].length, (new int[2][2])[0].length);

        systemInMock.provideLines("3", "5");
        int a[][] = m.paral(0);
        systemInMock.provideLines("7", "4");
        int b[][] = m.paral(0);
        assertEquals(m.max(a, b).length, new int[3][4].length);
        assertEquals(m.max(a, b)[0].length, (new int[3][4])[0].length);
    }

    @Test
    public void testMet_3_start() {
        Throwable a = new Throwable();
        try {
            systemInMock.provideLines("10000", "9000", "9000", "10000");
            m.start();
        } catch (Throwable e) {
            a = e;
        } finally {
            assertEquals(a.getCause(), null);
        }
    }


    @Test
    public void testMet_4_paral() throws IOException {
        start = 0;
		size = 8;
		step = 2;
		
		fw = new FileWriter("test1.txt");
		val = new int[]{10, 100, 1000, 10000};
		normVal = new BigDecimal[(size - start + step) / step][4];
		long st, en;
		System.out.println("Для определения эффективности разного количества потоков при вычислении матриц, будет " +
		"проведено " + (size - start) / step + " иттераций: ");
		for (int i = start, h = 0; i < size + step; i += step, h++) {
			st = System.nanoTime();
			fw.write(i + " поток:\n");
			m = new Mul2matrix();
			multipotok(i);
			m = null;
			en = System.nanoTime();
			System.out.format("%s%-2d%s%-3d%s%-2d%s%-4.1f%s", "   ", h, "итерация (", i," поток) завершена. " +
			"Осталось - ", ((size – start)/ step - h), " (примерно ", (((double) (en - st) / 1000000000) *
			((size - start) / step - h)), " сек)\n");
		}
		end();
		fw.close();
	}


    private void multipotok(int ind) throws IOException {
        numPotok++;
        long st, en;
        double re, sum = 0;

        for (int i = 0; i < val.length; i++, sum = 0) {
            fw.write("Матрица: " + val[i] + "x" + val[i] + "\n");
            for (int j = 0; j < 10; j++) {
                systemInMock.provideLines(String.valueOf(val[i]), String.valueOf(val[i]));
                st = System.nanoTime();
                m.paral(ind);
                en = System.nanoTime();
                re = (double) (en - st) / 1000000000;
                sum += re;
                fw.write(BigDecimal.valueOf(re).setScale(10, BigDecimal.ROUND_HALF_UP) + "\n");
            }
            normVal[numPotok][i] = BigDecimal.valueOf(sum / 10);
            fw.write("Среднее зеначение: " + (BigDecimal.valueOf(sum / 10).setScale(10, BigDecimal.ROUND_HALF_UP)) + " сек\n\n");
        }
    }


    private void end() throws IOException {
        String[] matr = {"   10x10   ", "  100x100  ", " 1000x1000 ", "10000x10000"};
		fw.write("\nЛучшие результаты вычисления матриц:\n");
        System.out.println("\nЛучшие результаты вычисления матриц (" + start + "-" + size + " потоков с шагом +"
                + step + "):\n");

        for (int i = 0; i < normVal[0].length; i++) {
            BigDecimal rez = normVal[0][i];
            int num = 0;
            for (int j = 1; j < normVal.length; j++)
                if (rez.compareTo(normVal[j][i]) == 1) {
                    num = j;
                    rez = normVal[j][i];
                }
            fw.write(matr[i] + " - " + (num * step) + start + " поток - " + rez.setScale(10,
                    BigDecimal.ROUND_HALF_UP) + " сек\n");
            System.out.format("%s%s%-3d%s%.8f%s", matr[i], " - ", (num * step) + start, " поток - ", rez, " сек\n");
        }
    }
}
