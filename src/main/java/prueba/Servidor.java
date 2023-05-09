package prueba;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Servidor {
    private final static List<ClienteRunnable> clientes = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        ServerSocket listener = new ServerSocket(59001);
        System.out.println("El servidor está iniciado...");
        try {
            while (true) {
                Socket socket = listener.accept();
                ClienteRunnable cliente = new ClienteRunnable(socket);
                Thread clienteThread = new Thread(cliente);
                clienteThread.start();
            }
        } finally {
            listener.close();
        }

    }

    private static class ClienteRunnable implements Runnable {
        private final Socket socket;
        private String ip;
        private String nickname;
        private String uuid;
        private String status;

        private Scanner in;
        private PrintWriter out;

        public ClienteRunnable(Socket socket) throws IOException {
            this.socket = socket;
            this.in = new Scanner(socket.getInputStream());
            this.out = new PrintWriter(socket.getOutputStream(), true);
        }

        @Override
        public void run() {
            try {
                out.println("Ingresa tu nickname: ");
                nickname = in.nextLine();
                uuid = java.util.UUID.randomUUID().toString();

                out.println("Tu UUID es: " + uuid);

                clientes.add(this);
                enviarMensajeTodos(nickname + " se ha conectado.");

                enviarMensajeTodos("Clientes conectados: " + obtenerNombresConectados());

                while (true) {
                    String input = in.nextLine();
                    if (input.toLowerCase().startsWith("/quit")) {
                        return;
                    }
                    enviarMensajeTodos(nickname + ": " + input);
                }
            } finally {
                try {
                    clientes.remove(this);
                    enviarMensajeTodos(nickname + " se ha desconectado.");
                    socket.close();
                } catch (IOException e) {
                    System.out.println("Error al cerrar la conexión: " + e.getMessage());
                }
                clientes.remove(this);
                enviarMensajeTodos("El usuario " + nickname + " se ha desconectado.");
            }
        }

    }

    private static void enviarMensajeTodos(String mensaje) {
        for (ClienteRunnable client : clientes) {
            client.out.println(mensaje);
        }
    }

    private static String obtenerNombresConectados() {
        StringBuilder nombresConcatenados = new StringBuilder();
        for (ClienteRunnable client : clientes) {
            nombresConcatenados.append(client.nickname).append(", ");
        }
        return nombresConcatenados.toString();
    }

}