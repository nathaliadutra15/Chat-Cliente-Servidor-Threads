package threads;

public class myThread extends Thread { // Herança

    public myThread(String nome) {
        super(nome);
    }

    // sobrescrita do método run - equivalente ao main()
    @Override
    public void run() {
        System.out.println("Thread " + getName() + " iniciou.");

        try {
            sleep(2000); // "dorme" por 2 segundos
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Thread " + getName() + " terminou.");
    }

}