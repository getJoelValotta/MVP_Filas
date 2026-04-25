package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.io.IOException;

import modelo.GestorFila;

public class EscuchaPuesto implements Runnable {
    private int PORT = 888;
    private String IP = "localhost";
    private GestorFila gestorFila;
    // Set de numeros de puesto
    private TreeSet<Integer> puestosDisponibles = new TreeSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));

    // Hashmap con numero de puesto + puestoHandler perteneciente a dicho puesto
    private ConcurrentHashMap<Integer, ManejaPuesto> manejadoresPuestos = new ConcurrentHashMap<>();

    public EscuchaPuesto(GestorFila gestorFila) {
        this.gestorFila = gestorFila;
    }

    public int getNumeroPuesto() {
        // TODO: throws!???
        int num = -1;
        try {
            num = puestosDisponibles.first();
            puestosDisponibles.remove(num);
        } catch (Exception e) {
            System.out.println("No hay puestos disponibles");
        }
        return num;
    }

    public void sacaNumPuesto(int num) {
        puestosDisponibles.add(num);
    }

    // METODO QUE ACTUALIZA A TODOS LOS PUESTOS EL NUMERO DE CLIENTES.
    private void actualizarClientesEspera(int numClientesEspera) {
        for (ManejaPuesto puesto : manejadoresPuestos.values()) {
            puesto.mandaNumClientesEspera(numClientesEspera);
        }
    }

    public void run() { // se inicia: new Thread(new EscuchaTotem()).start();)
        ServerSocket serverSocket = null;
        System.out.println("Escuchando puestos...");
        try {
            serverSocket = new ServerSocket(PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                // Este codigo se corre una vez por cada puesto conectado al server
                int numPuesto = getNumeroPuesto();
                ManejaPuesto manejaPuesto = new ManejaPuesto(socket, numPuesto, gestorFila, this);
                manejadoresPuestos.put(numPuesto, manejaPuesto);
                manejaPuesto.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public GestorFila getGestorFila() {
        return gestorFila;
    }

    public int getPORT() {
        return PORT;
    }

    public String getIP() {
        return IP;
    }

    public void setGestorFila(GestorFila gestorFila) {
        this.gestorFila = gestorFila;
    }

    public void setPORT(int pORT) {
        PORT = pORT;
    }

    public void setIP(String iP) {
        IP = iP;
    }

}