import java.io.*;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import Exceptions.*;

public class Initialization {
    private Map<String, Map<String, String>> fileContent;

    Initialization(final String fileName) throws FileNotFound, IOException {
        if (!(new File(fileName)).exists()) {
            throw new FileNotFound(fileName);
        }
        fileContent = new HashMap<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        String curSection = "";
        while (bufferedReader.ready()) {
            String curLine = bufferedReader.readLine();
            curLine = deleteComments(curLine);
            if (!lineIsEmpty(curLine)) {
                if (lineIsSection(curLine)) {
                    curSection = extractSection(curLine);
                    fileContent.put(curSection, new HashMap<>());
                }else {
                    AbstractMap.SimpleEntry<String, String> parameter = extractParameter(curLine);
                    fileContent.get(curSection).put(parameter.getKey(), parameter.getValue());
                }
            }
        }
    }

    private String deleteComments(String str) {
        int posBegin = str.indexOf(";");
        if (posBegin != -1) {
            str = str.substring(0, posBegin);
        }
        return str;
    }

    private String extractSection(String str) {
        int posBegin = str.indexOf("[");
        int posEnd = str.indexOf("]");
        return str.substring(posBegin + 1, posEnd);
    }

    private boolean lineIsEmpty(final String line) {
        int minLineLength = 3;
        return line.length() < minLineLength;
    }

    private boolean lineIsSection(final String line) {
        return line.contains("[");
    }

    private AbstractMap.SimpleEntry<String, String> extractParameter(String line) {
        String[] parts = line.split("=");
        return new AbstractMap.SimpleEntry<>(parts[0].trim(), parts[1].trim());
    }

    private void existenceOfPair(final String sectionName, final String parameterName) throws InvalidParameterName, InvalidSectionName {
        if (!isSection(sectionName)) {
            throw new InvalidSectionName(sectionName);
        }
        if (!isParameter(sectionName, parameterName)) {
            throw new InvalidParameterName(parameterName);
        }
    }

    public boolean isSection(final String sectionName) {
        return fileContent.containsKey(sectionName);
    }

    public boolean isParameter(final String sectionName, final String parameterName) throws InvalidSectionName {
        if (!isSection(sectionName)) {
            throw new InvalidSectionName(sectionName);
        }
        return fileContent.get(sectionName).containsKey(parameterName);
    }

    public String getString(final String sectionName, final String parameterName) throws InvalidParameterName, InvalidSectionName {
        existenceOfPair(sectionName, parameterName);
        return fileContent.get(sectionName).get(parameterName);
    }

    public int getInteger(final String sectionName, final String parameterName) throws InvalidParameterName, InvalidSectionName, InvalidParameterType {
        existenceOfPair(sectionName, parameterName);
        String stringValue = fileContent.get(sectionName).get(parameterName);
        try {
            return Integer.parseInt(stringValue);
        } catch (Exception ex) {
            throw new InvalidParameterType(stringValue);
        }
    }

    public double getDouble(final String sectionName, final String parameterName) throws InvalidParameterName, InvalidSectionName, InvalidParameterType {
        existenceOfPair(sectionName, parameterName);
        String stringValue = fileContent.get(sectionName).get(parameterName);
        try {
            return Double.parseDouble(stringValue);
        } catch (Exception ex) {
            throw new InvalidParameterType(stringValue);
        }
    }

}
