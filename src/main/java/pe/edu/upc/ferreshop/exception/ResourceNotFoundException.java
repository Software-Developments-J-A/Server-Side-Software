package pe.edu.upc.ferreshop.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(){
        super();
    }

    public ResourceNotFoundException(String message){
        super(message);
    }
}

