import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;

interface Program {
    int getChannel();

    LocalDateTime getStartTime();

    LocalDateTime getEndTime();

    int getPriority();
}

class Prog implements Program {

    private int chanal, prioritet;
    private LocalDateTime start, end;

    Prog(int chanal, int prioritet, LocalDateTime start, LocalDateTime end) {
        this.chanal = chanal;
        this.prioritet = prioritet;
        this.start = start;
        this.end = end;
    }

    @Override
    public int getChannel() {
        return chanal;
    }

    @Override
    public LocalDateTime getStartTime() {
        return start;
    }

    @Override
    public LocalDateTime getEndTime() {
        return end;
    }

    @Override
    public int getPriority() {
        return prioritet;
    }
}

public class zadanie {
    public static void main(String[] args) {
        Random r = new Random();
        Prog[] teleprogramm = new Prog[9];
        String[] sortTime = new String[teleprogramm.length];

        System.out.println("Список всех программ:");
        for (int i = 0; i < teleprogramm.length; i++) {
            int h = r.nextInt(23);
            teleprogramm[i] = new Prog(i + 1, r.nextInt(10),
                    LocalDateTime.of(2018, 4, 30, h, r.nextInt(59)),
                    LocalDateTime.of(2018, 4, 30, h + 1, r.nextInt(59)));
            sortTime[i] = teleprogramm[i].getStartTime().toString();

            System.out.println(teleprogramm[i].getChannel() + " канал - " + teleprogramm[i].getPriority() +
                    " приоритет - время программы: " + teleprogramm[i].getStartTime().getHour() + ":" +
                    teleprogramm[i].getStartTime().getMinute() + " - " +
                    teleprogramm[i].getEndTime().getHour() + ":" + teleprogramm[i].getEndTime().getMinute());
        }

        Arrays.sort(sortTime);
        Prog[] st = new Prog[teleprogramm.length];
        for (int i = 0, p = 0; i < teleprogramm.length; i++) {
            for (int j = 0; j < teleprogramm.length; j++)
                if (sortTime[i].compareTo(teleprogramm[j].getStartTime().toString()) == 0) p = j;
            st[i] = teleprogramm[p];
        }

        Prog[] finalTeleprogram = new Prog[st.length];
        for (int i = 0; i < finalTeleprogram.length - 1; i++) {
            if (st[i].getPriority() > st[i + 1].getPriority() &&
                    st[i].getEndTime().toString().compareTo(st[i + 1].getStartTime().toString()) > 0) {
                finalTeleprogram[i] = st[i];
                finalTeleprogram[i + 1] = new Prog(st[i + 1].getChannel(), st[i + 1].getPriority(), st[i].getEndTime(), st[i + 1].getEndTime());
                i++;
            } else {
                finalTeleprogram[i] = st[i];
                finalTeleprogram[i + 1] = st[i + 1];
            }
        }


        System.out.println("\nНаша Телепрограмма:");
        for (int i = 0; i < finalTeleprogram.length; i++)
            try {
                System.out.println((i + 1) + ") " + finalTeleprogram[i].getChannel() + " канал в " +
                        finalTeleprogram[i].getStartTime().getHour() + ":" + finalTeleprogram[i].getStartTime().getMinute());
            } catch (NullPointerException exc) {
                System.out.println((i + 1) + ") " + st[i].getChannel() + " канал в " +
                        st[i].getStartTime().getHour() + ":" + st[i].getStartTime().getMinute());
            }

    }
}
