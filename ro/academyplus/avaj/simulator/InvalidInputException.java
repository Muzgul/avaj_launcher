package ro.academyplus.avaj.simulator;

public class InvalidInputException extends Exception {
    static final long serialVersionUID = 0;

    public InvalidInputException(String message){
        super(message);
    }
}