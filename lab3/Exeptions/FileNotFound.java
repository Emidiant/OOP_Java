package Exceptions;

public class FileNotFound extends Exception{
    public FileNotFound(String fileName) {
        super(fileName + " doesn't found or can't be read");
    }
}
