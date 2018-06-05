class ParalPot implements Runnable {

    private int size,st,arr[][];

    ParalPot(int start, int size, int[][] massiv){
        st=start;
        this.size = size;
        arr=massiv;
        new Thread(this).start();
    }

    @Override
    public void run() {
        for(int i=st;i<size;i++)
            for (int j = 0; j < arr[i].length; j++)
                arr[i][j] = j + (i * arr[i].length);
    }
}
