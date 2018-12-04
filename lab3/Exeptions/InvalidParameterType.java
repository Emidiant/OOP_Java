package Exceptions;

public class InvalidParameterType extends Exception {
    public InvalidParameterType(String parameter) {
        super("Can't output line: " + parameter);
    }
}
