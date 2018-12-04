package Exceptions;

public class InvalidParameterName extends Exception {
    public InvalidParameterName(String parameterName) {
        super("Parameter " + parameterName + " not found");
    }
}
