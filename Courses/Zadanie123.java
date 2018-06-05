import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;
import static java.lang.Math.abs;

public class Zadanie123 {

    static String[] inp() throws IOException {
        int g, f, h;
        char ch;
        String s, ci = "", cd = "";
        do {
            g = f = h = 0;
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            s = reader.readLine();

            for (int i = 0; i < s.length(); i++) {
                ch = s.charAt(i);
                if (ch != '-' && ch != '.' && (ch < '0' || ch > '9') || s.charAt(0) == '.' || s.charAt(s.length() - 1) == '.') h++;
                if (ch == '.') g++;
                if (i < s.length() - 1) if (s.charAt(i + 1) == '-') h++;
            }
            if (g > 1 || h > 0 || s.trim().isEmpty() || s.equals("-")) System.out.println("Нужно вводить число");
            else {
                for (int i = 0; i < s.length(); i++) {
                    if (g == 0) { ci += s.charAt(i); f++; }
                    else { cd += s.charAt(i); f++; }
                }
            }
        } while (f != s.length()||f==0);
        if (cd.compareTo("") == 0) return new String[]{ci, "I"};
        else return new String[]{cd, "D"};
    }

    public void zadanie123() throws IOException {

        System.out.println("Задание 1.");
        Random r = new Random();
        int b = 0, c = 0, sl = 0, sr = 0, ar[];

        do {
            Scanner a = new Scanner(System.in);
            System.out.println("Введите четное число >0: ");
            if (a.hasNextInt()) {
                c = a.nextInt();
                if ((c % 2) == 0 && c > 0) b++;
            }
        } while (b == 0);

        ar = new int[c];
        System.out.print("[");
        for (int i = 0; i < ar.length; i++) {
            ar[i] = r.nextInt(11) - 5;

            if (i < ar.length - 1) System.out.print(ar[i] + ", ");
            else System.out.println(ar[i] + "]");

            if (i < ar.length / 2) sl += abs(ar[i]);
            else sr += abs(ar[i]);
        }
        if (sl > sr) System.out.println("Сумма модулей левой половины больше правой: " + sl + " > " + sr);
        if (sl < sr) System.out.println("Сумма модулей правой половины больше левой: " + sl + " < " + sr);
        if (sl == sr) System.out.println("Сумма модулей левой и правой половины равны: " + sl + " == " + sr);


        ///////////////////////////////////////////////

        System.out.print("\n\nЗадание 2.\n");
        int s = 0, arr[] = new int[12];

        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            if (i < 6) {
                do {
                    arr[i] = r.nextInt(21) - 10;
                } while (arr[i] == 0);
                if (arr[i] < 0) s += 1;
            } else {
                if (s < 6) {
                    arr[i] = r.nextInt(10) - 10;
                    s++;
                } else arr[i] = r.nextInt(10) + 1;
            }
            if (i < 11) System.out.print(arr[i] + ", ");
            else System.out.println(arr[i] + "]");
        }


        //////////////////////////////////////////

        System.out.print("\n\nЗадание 3.\n");
        int f, cc;

        do {
            Calculator cal = new Calculator();
            f=cc=0;
            System.out.println("\nВыберите действие:\n1 - Сложение\n2 - Вычитание\n3 - Произведение\n4 - Деление\n5 - Возведение в степень\n" +
                    "6 - Факториал\n7 - Остаток от деления\n8 - Взятие модуля\n9 - Выход");
            do {
                Scanner a = new Scanner(System.in);
                if (a.hasNextInt()) {
                    cc = a.nextInt();
                    if (cc > 0 && cc < 10) f++;
                }
                if(f==0) System.out.println("Можно вводить только указанные в перечне числа");
            } while (f == 0);

            switch (cc) {
                case 1:
                    System.out.println("\nВы выбррали сумму.\nВведите 2 числа");
                    cal.sum(inp(), inp());
                    break;
                case 2:
                    System.out.println("\nВы выбррали вычитание.\nВведите 2 числа");
                    cal.sub(inp(), inp());
                    break;
                case 3:
                    System.out.println("\nВы выбррали произведение.\nВведите 2 числа");
                    cal.mul(inp(), inp());
                    break;
                case 4:
                    System.out.println("\nВы выбррали деление.\nВведите 2 числа");
                    cal.del(inp(), inp());
                    break;
                case 5:
                    System.out.println("\nВы выбррали возведение в степень.\nВведите 2 числа");
                    cal.exp(inp(), inp());
                    break;
                case 6:
                    System.out.println("\nВы выбррали факториал.\nВведите 1 число");
                    cal.fact(inp());
                    break;
                case 7:
                    System.out.println("\nВы выбррали остаток от деления.\nВведите 2 числа");
                    cal.ost(inp(), inp());
                    break;
                case 8:
                    System.out.println("\nВы выбррали Взятие модуля.\nВведите 1 число");
                    cal.mod(inp());
                    break;
            }
        } while (cc != 9);
    }
}

