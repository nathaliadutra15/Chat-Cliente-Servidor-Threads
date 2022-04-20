package chat.server;

import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class AtendeCli extends Thread {
    private Socket cliente;
    private Scanner input = null;
    private PrintStream output = null;
    private ArrayList<AtendeCli> threads = new ArrayList<>(); 

    // Receber o cliente
    public AtendeCli(Socket cliente, ArrayList<AtendeCli> threads) {
        this.cliente = cliente;
        this.threads = threads;
    }

    @Override
    public void run() {
        // Comunicação
        try {
            // Ler as mensagens dentro deste canal de comunicação
            input = new Scanner(cliente.getInputStream());
            output = new PrintStream(cliente.getOutputStream());

            // Enviar a mensagem dentro deste canal de comunicação
            String mensagem;
            do {
                mensagem = input.nextLine();
                System.out.println("Recebido: " + mensagem);
                for (AtendeCli atendeCli : threads) {
                    atendeCli.enviaMensagem(mensagem);
                }      

            } while (!mensagem.equalsIgnoreCase("exit"));

            System.out.println("Conexão encerrada com " + cliente.getInetAddress().getHostAddress());
            
            input.close();
            cliente.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void enviaMensagem(String mensagem) {
        output.println("> " + mensagem); // Printa  mensagem recebida
    }

}
