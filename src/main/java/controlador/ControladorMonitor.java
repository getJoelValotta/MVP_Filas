package controlador;

import javax.swing.SwingUtilities;

import interfaces.EscuchadorDeSocket;
//import modelo.ReceptorDatosMonitor;
import vistas.MonitorGUI;

public class ControladorMonitor implements EscuchadorDeSocket {

    private MonitorGUI vista;
    // private ReceptorDatosMonitor receptorDatosMonitor;
    // private final int PORT = 999;

    public ControladorMonitor(MonitorGUI vista) {
        this.vista = vista;
    }

    public void accionRealizada(String dniRecibido, String puesto) {
        if (dniRecibido != null && !dniRecibido.isEmpty() && puesto != null && !puesto.isEmpty()) {
            // Sincronizamos con el hilo de la GUI para actualizar las etiquetas
            SwingUtilities.invokeLater(() -> {
                vista.registrarLlamado(dniRecibido, puesto);
            });
        }
    }

}
/*
 * private void iniciarServidorTCP() {
 * // Hilo separado para no congelar la interfaz gráfica [cite: 24]
 * new Thread(() -> {
 * try (ServerSocket server = new ServerSocket(PORT)) {
 * System.out.println("Monitor escuchando en puerto " + PORT);
 * 
 * while (true) {
 * // Se queda esperando a que el Puesto envíe un DNI
 * try (Socket socketCliente = server.accept();
 * BufferedReader in = new BufferedReader(new
 * InputStreamReader(socketCliente.getInputStream()))) {
 * 
 * String dniRecibido = in.readLine();
 * 
 * if (dniRecibido != null && !dniRecibido.isEmpty()) {
 * // Sincronizamos con el hilo de la GUI para actualizar las etiquetas
 * SwingUtilities.invokeLater(() -> {
 * vista.registrarLlamado(dniRecibido,"1");
 * });
 * }
 * } catch (Exception e) {
 * System.err.println("Error al procesar conexión: " + e.getMessage());
 * }
 * }
 * } catch (Exception e) {
 * e.printStackTrace();
 * }
 * }).start();
 * }
 * 
 */