import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class Mul2matrix {

    static int n = 0;

    public int[][] check() {
        n++;
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
        for (int f = 0, x = 0, y = 0; ; ) {
            System.out.println("\nВведите " + (f + 1) + " размер " + n + " массива:");
            try {
                int c = Integer.parseInt(buf.readLine());
                if (c <= 0) throw new Throwable();
                if (f == 0) x = c;
                if (f == 1) y = c;
                f++;
                if (f > 1)
                    if ((long) x * (long) y > 2147483645) {
                        System.out.println("Размер полученного массива выходит за допустимые рамки: arr[x][y], где x*y < maxInt");
                        f = 0;
                    } else {
                    System.out.println("Вы ввели массив размера [" + x + "][" + y + "]");
                    return new int[x][y];
                    }
            } catch (Throwable e) {
                System.out.println("Нужно вводить целое число >0");
            }
        }
    }

    public int[][] paral(int potoks) {
        int[][] arr = check();
        if (potoks == 0) {
            for (int i = 0; i < arr.length; i++)
                for (int j = 0; j < arr[i].length; j++)
                    arr[i][j] = j + (i * arr[i].length);
        } else if (potoks > arr.length) {
            for (int k = 0; k < arr.length; k++)
                new ParalPot(k, arr.length - (arr.length - (k + 1)), arr);
        } else {
            int rez = arr.length / potoks;
            for (int k = 0; k < arr.length; k += rez)
                if (k + rez <= arr.length) new ParalPot(k, k + rez, arr);
                else new ParalPot(k, arr.length, arr);
        }
        for (int i = 0; i < arr.length ; i++)
            for (int j = 0; j < arr[i].length; j++) {
                System.out.format("%-7d", arr[i][j]);
                if (j == arr[0].length - 1) System.out.println();
            }

        return arr;
    }

    public int[][] max(int[][] x, int[][] y) {
        return new int[Math.min(x.length, y.length)][Math.min(x[0].length, y[0].length)];
    }

    public void start() {

        //Задание 7. Умножение двух матриц.
        long st, en;
        int arr[][] = paral(0);
        int arr2[][] = paral(0);

        System.out.println("\nПроизведение массивов: ");
        int[][] rez = max(arr, arr2);
        System.out.println("arr[" + arr.length + "][" + arr[0].length + "] * " + "arr2[" + arr2.length + "][" +
                arr2[0].length + "] = rez[" + rez.length + "][" + rez[0].length + "]");

        st = System.nanoTime();

        for (int i = 0; i < rez.length; i++)
            for (int j = 0; j < rez[0].length; j++) {
                rez[i][j] = arr[i][j] * arr2[i][j];
                System.out.format("%-7d",rez[i][j]);
                if (j == rez[0].length - 1) System.out.println();
            }
        en = System.nanoTime();
        System.out.println("Время произведения матриц составило: " + BigDecimal.valueOf(((double) (en - st) / 1000000000)).setScale(10,
                BigDecimal.ROUND_HALF_UP) + " сек\n");
    }

}
