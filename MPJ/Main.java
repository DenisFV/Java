import mpi.Intracomm;
import mpi.MPI;
import java.math.BigDecimal;
import java.util.stream.LongStream;

public class Main {

 public static void fun(int ar[], int start, int rank){
        for (int i = 0; i < ar.length; i++)
            ar[i] = (ar.length * rank) + i + 1 + (start * ar.length);
 }
 
 public static void recvsend2(int[] buf, int rez[][], int rank, int arrsize){
     for (int i = arrsize * rank; i < arrsize * (rank + 1); i++) {
         fun(buf, i, rank);
         MPI.COMM_WORLD.Sendrecv(buf, 0, buf.length, MPI.INT, rank, 0,
                                 rez[i], 0, rez[i].length, MPI.INT, rank, 0); 
     }
 }


 public static void main(String[] args) {
     long time = 0;
     int[] n = {10, 100, 1000, 7000};
     for (int m = 3; m < n.length; m++) {
        for (int k = 0; k < 10; k++) {

          MPI.Init(args);
          final Intracomm comm = MPI.COMM_WORLD;
          final int rank = comm.Rank();
          final int size = comm.Size();
          if (rank==0 && k == 0 && m == 0) System.out.println("Proc: "+size);

          int size1 = n[m];
          int size2 = n[m];
          int arr[][]    = new int[size1][size2];
          int buf[]      = new int[size2];
          int bufrez[][] = new int[size1][size2];

          long st = System.nanoTime();
          comm.Bcast(buf, 1, buf.length - 1, MPI.INT, 0);
          recvsend2(buf, bufrez, rank, arr.length / size);
          for (int i = 0; i < bufrez.length; i++)
              comm.Reduce(bufrez[i], 0, arr[i], 0, bufrez[i].length, MPI.INT, MPI.MAX, 0);

          if (rank == 0) {
            for (int i = 0; i < arr.length; i++)
              for (int j = 0; j < arr[0].length; j++)
                arr[i][j] = arr[i][j] * arr[i][j];
          }
                  
          long en = System.nanoTime();
          time += (en - st);

          long buftime[] = new long[size];
          long timerez[] = new long[size];

          comm.Bcast(buftime, 1, buftime.length - 1, MPI.LONG, 0);
          buftime[rank] = time;
          MPI.COMM_WORLD.Sendrecv(buftime,0, buftime.length, MPI.LONG, rank, 1, buftime,0, buftime.length, MPI.LONG, rank, 1);
          comm.Reduce(buftime, 0, timerez, 0, buftime.length, MPI.LONG, MPI.MAX, 0);


          if (k == 4) {
              System.out.println("matrx "+n[m]+"X"+n[m]+" - "+rank+" proc: " +
                  BigDecimal. valueOf(((double)time/10000000)/size).setScale(10,BigDecimal.ROUND_HALF_UP));
          }

          if (k == 4 && rank == 0) {
            long rez = LongStream.of(timerez).sum() / size;
            System.out.println("best: matrx "+n[m]+"X"+n[m]+" - "+BigDecimal.valueOf((double) rez / 1000000000 /
                 size). setScale(10, BigDecimal.ROUND_HALF_UP));
          }
          MPI.Finalize();
      }
    }
  }
}
