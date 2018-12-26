package Exceptions;

import java.util.HashMap;

public class Parameter {
    private String name;
    private String value;

    Parameter(String parameter){
        String[] parts = parameter.split("=");
        this.name = parts[0].trim();
        this.value = parts[1].trim();
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return (name + " = " + value);
    }
}
