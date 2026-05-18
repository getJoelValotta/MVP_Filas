package server;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EnviaHeartBeat implements Runnable {

    private final int PORT = 1212;

    @Override
    public void run() {

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            while (true) {

                Socket socket = serverSocket.accept();

                PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
                out.println("OK");

                socket.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}