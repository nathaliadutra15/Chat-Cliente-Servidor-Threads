package chat.client;

import java.net.Socket;
import java.util.Scanner;

public class Escuta extends Thread {
    private Socket socket;
    private Scanner input = null;

    public Escuta(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            input = new Scanner(socket.getInputStream());
            String mensagem;
            
            do {
                // Recebe mensagem do servidor
                mensagem = input.nextLine();
                System.out.println(mensagem);

            } while (true);
            //input.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
