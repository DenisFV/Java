import java.util.*;

public class Egor {
    public static void run() {

        /*
        * Вводим целое число N (1-100) - количество друзей егора
        * Далее идет цикл размера N:
            * Сохраняем id друга Егора (1000-9999) в массив ar[], чтобы потом удалит все вхождения их id
            * Вводим число F (1-100) - друзья друзей
            * Записываем id друзей друзей в лист rez
        * Пробегаемся по вему листу rez и удаляем все элементы, хранящиеся в ar[]
        * В итоге получаем лист rez, в которм находяттся только id друзей друзей
        * Выводим размер этого листа.
        */
        Scanner s = new Scanner(System.in);
        int ar[] = new int[s.nextInt()];
        Set<Integer> rez = new HashSet<Integer>();
        for (int i = 0; i < ar.length; i++) {
            ar[i] = s.nextInt();
            for (int j = 0, n = s.nextInt(); j < n; j++) rez.add(s.nextInt());
        }
        for (int k = 0; k < ar.length; k++) rez.remove(ar[k]);
        System.out.println(rez.size());

    }
}
