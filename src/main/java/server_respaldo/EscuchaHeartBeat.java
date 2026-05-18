package server_respaldo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;



public class EscuchaHeartBeat implements Runnable {
    private final String IP_PRINCIPAL = "localhost";
    private final int PORT = 1212;
    private MonitorRespaldo monitorRespaldo;


    public EscuchaHeartBeat(MonitorRespaldo monitorRespaldo) {
        this.monitorRespaldo = monitorRespaldo;
    }


    @Override
    public void run() {

        // Espera hasta que el principal responda por primera vez
        System.out.println("Esperando que el servidor principal levante...");
        boolean principalListo = false;
        while (!principalListo) {
            try (
                Socket socket = new Socket(IP_PRINCIPAL, PORT);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
            ) {
                socket.setSoTimeout(300);
                String respuesta = in.readLine();
                if ("OK".equals(respuesta)) {
                    principalListo = true;
                    System.out.println("Servidor principal detectado. Iniciando monitoreo...");
                }
            } catch (Exception e) {
                System.out.println("Principal no disponible aún, reintentando...");
            }

            if (!principalListo) {
                try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }

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
                    cantErrores = 0;
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
        monitorRespaldo.notificarCaida();

        System.out.println("Servidor principal inactivo. Iniciando servidor de respaldo...");

    }

}
