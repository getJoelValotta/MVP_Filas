package interfaces;


//Metodo similar a un ActionListener para el monitor que necesita enterarse de los eventos segun principio SOLID
public interface EscuchadorDeSocket {

    public void accionRealizada(String dni, String puesto);
}
