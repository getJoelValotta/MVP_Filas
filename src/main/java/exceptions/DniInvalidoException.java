package exceptions;

import modelo.Cliente;

public class DniInvalidoException extends Exception{
    private String dni;

    public DniInvalidoException(String dni){
        super(Cliente.msgB);
        this.dni = dni;
    }

}
