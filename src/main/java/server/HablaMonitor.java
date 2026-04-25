package server;

import java.net.ServerSocket;
import java.net.Socket;

import modelo.GestorFila;

public class HablaMonitor implements Runnable{
    private GestorFila gestorFila;
    private int PORT = 999;
    private String IP = "localhost";

    public HablaMonitor(GestorFila gestorFila) {
        this.gestorFila = gestorFila;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        
    }
}