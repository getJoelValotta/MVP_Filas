package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class EscuchaHeartBeat implements Runnable {
    private final String IP_PRINCIPAL = "localhost";
    private final int PORT = 999;


    @Override
    public void run() {
        int cantErrores = 0;
        while (cantErrores < 3) {

            try (
                Socket socket = new Socket(IP_PRINCIPAL, PORT);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()))
            ) {

                socket.setSoTimeout(300);

                String respuesta = in.readLine();

                if ("OK".equals(respuesta)) {
                    System.out.println("Servidor principal activo");
                }

            } catch (Exception e) {
                System.out.println("No responde el servidor principal");
                cantErrores++;
            }

            try {
                Thread.sleep(500); // 0.5 segundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        

    }
}
