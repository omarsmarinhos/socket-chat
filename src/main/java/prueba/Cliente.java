package prueba;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        String serverAddress = "localhost";
        Socket socket = new Socket(serverAddress, 59001);

        try {
            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String nickname = null;
            String uuid = null;
            do {
                String line = in.nextLine();
                if (line.startsWith("Ingresa tu nickname: ")) {
                    System.out.print(line);
                    nickname = scanner.nextLine();
                    out.println(nickname);
                } else if (line.startsWith("Tu UUID es: ")) {
                    uuid = line.substring("Tu UUID es: ".length());
                } else if (line.startsWith("Clientes conectados: ")) {
                    System.out.println(line);
                } else {
                    System.out.println(line);
                }

            } while (nickname == null || uuid == null);

            Thread incomingThread = new Thread(new IncomingReader(in));
            incomingThread.start();

            while (true) {
                String message = scanner.nextLine();
                out.println(message);
            }
        } finally {
            socket.close();
        }

    }

    private static class IncomingReader implements Runnable {
        private final Scanner in;

        public IncomingReader(Scanner in) {
            this.in = in;
        }

        @Override
        public void run() {
            while (in.hasNextLine()) {
                String line = in.nextLine();
                System.out.println(line);
            }
        }
    }

}