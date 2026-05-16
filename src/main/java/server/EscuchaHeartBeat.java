package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

import modelo.GestorFila;

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
                } else {
                    System.out.println("Respuesta inesperada del servidor principal");
                    cantErrores++;
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

        System.out.println("Servidor principal inactivo. Iniciando servidor de respaldo...");


        

    }


    public void iniciaRespaldo() {
        GestorFila colaClientes = new GestorFila();
        HablaMonitor hablaMonitor = new HablaMonitor(colaClientes);
        EscuchaPuesto escuchaPuesto = new EscuchaPuesto(colaClientes, hablaMonitor);
        EscuchaTotem escuchaTotem = new EscuchaTotem(colaClientes);
        new Thread(hablaMonitor).start();
        new Thread(escuchaPuesto).start();
        new Thread(escuchaTotem).start();
    }
}
