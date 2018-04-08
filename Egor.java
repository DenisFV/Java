import java.util.*;

public class Egor {
    public static void run() {

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
