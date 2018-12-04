package Exceptions;

public class InvalidSectionName extends Exception{

    public InvalidSectionName(String sectionName) {
        super("Section " + sectionName + " not found");
    }
}
