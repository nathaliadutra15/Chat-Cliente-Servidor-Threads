package chat.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    static ArrayList<AtendeCli> threads = new ArrayList<>(); 

    public static void main(String[] args) {
        final int PORTA = 12345; // Constante com o número da porta
        ServerSocket serverSocket;
        Socket clientSocket = null;

        // Criar o socket e fazer o bind
        // try/catch para o tratamento da exceção
        try {
            serverSocket = new ServerSocket(PORTA);
        } catch (Exception e) {
            System.out.println("Porta " + PORTA + " já está em uso.");
            return;
        }

        // Aguardar pedido de conexão
        try {
            while (true) {
                System.out.println("Aguardando pedido de conexão...");
                clientSocket = serverSocket.accept(); // Atribuindo o retorno do socket do cliente
                System.out.println("Conectado com " + clientSocket.getInetAddress().getHostAddress());
                AtendeCli atendeCli = new AtendeCli(clientSocket, threads);
                threads.add(atendeCli);
                atendeCli.start();
            }
        } catch (Exception e) {
            System.out.println("Erro na conexão.");
            System.out.println(e.getMessage());
        }

        // Encerrar conexão
        try {
            serverSocket.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}