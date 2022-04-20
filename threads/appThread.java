package threads;

public class appThread {
    public static void main(String[] args) {
        myThread t1 = new myThread("T1");
        myThread t2 = new myThread("T2");
        myThread t3 = new myThread("T3");

        t1.start();
        t2.start();
        t3.start();



        System.out.println("Final do main");
    }
}
