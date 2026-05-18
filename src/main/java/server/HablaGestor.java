package server;
import java.io.PrintWriter;
import java.net.Socket;

public class HablaGestor {
    private final String IP = "localhost";
    private final int PORT = 1010;
    private Socket socket;


    public HablaGestor() {
        try {
            this.socket = new Socket(IP, PORT);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }   

    public void enviaDNI(Long dni) {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("agrega");
            out.println(dni);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void llamaSiguiente() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("llama");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
