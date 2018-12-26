import java.io.*;
import java.util.*;

import Exceptions.*;

public class Initialization {
    private List<Section> fileContent;

    Initialization(final String fileName) throws FileNotFound, IOException {
        if (!(new File(fileName)).exists()) {
            throw new FileNotFound(fileName);
        }
        fileContent = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        String curSection = "";

        boolean flagFirstSection = false;
        List<String> parameters = new ArrayList<>();
        String oldsection = "";

        while (bufferedReader.ready()){
            String curLine = bufferedReader.readLine();
            curLine = deleteComments(curLine);
            if (!lineIsEmpty(curLine)) {

                if (lineIsSection(curLine)){
                    if (flagFirstSection){
                        Section section = new Section();
                        section.addAll(parameters);
                        section.setSectionName(oldsection);
                        fileContent.add(section);

                        parameters.clear();
                    }
                    oldsection = curLine;

                    flagFirstSection = true;
                }else {
                    parameters.add(curLine);
                }
            }
        }
        Section section = new Section();
        section.addAll(parameters);
        section.setSectionName(oldsection);
        fileContent.add(section);
        /*for (Section section1: fileContent) {
            System.out.println(section1.getSectionName());
            for (Parameter parameter: section1.getParameters())
                System.out.println(parameter.toString());
        }*/
    }

    private String deleteComments(String str) {
        int posBegin = str.indexOf(";");
        if (posBegin != -1) {
            str = str.substring(0, posBegin);
        }
        return str;
    }

    private boolean lineIsEmpty(final String line) {
        int minLineLength = 3;
        return line.length() < minLineLength;
    }

    private boolean lineIsSection(final String line) {
        return line.contains("[");
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
        boolean haveSection = false;
        for (Section section: fileContent){
            if(section.getSectionName().equals(sectionName)){
                haveSection= true;
                break;
            }
        }
        return haveSection;
    }

    public boolean isParameter(final String sectionName, final String parameterName) throws InvalidSectionName {
        if (!isSection(sectionName)) {
            throw new InvalidSectionName(sectionName);
        }
        boolean haveParameter = false;
        for (Section section: fileContent){
            for (Parameter parameter : section.getParameters()) {
                if (parameter.getName().equals(parameterName)) {
                    haveParameter = true;
                    break;
                }
            }
        }
        return haveParameter;
    }

    public String getString(final String sectionName, final String parameterName) throws InvalidParameterName, InvalidSectionName {
        existenceOfPair(sectionName, parameterName);
        String getString = "";
        for (Section section: fileContent){
            for (Parameter parameter : section.getParameters()) {
                if (parameter.getName().equals(parameterName)) {
                    getString = parameter.getValue();
                    break;
                }
                if (getString != ""){
                    break;
                }
            }
        }
        return getString;
    }

    public int getInteger(final String sectionName, final String parameterName) throws InvalidParameterName, InvalidSectionName, InvalidParameterType {
        existenceOfPair(sectionName, parameterName);
        String getString = "";
        try {
            for (Section section: fileContent){
                for (Parameter parameter : section.getParameters()) {
                    if (parameter.getName().equals(parameterName)) {
                        getString = parameter.getValue();
                        break;
                    }
                    if (getString != ""){
                        break;
                    }
                }
            }
            return Integer.parseInt(getString);
        } catch (Exception ex) {
            throw new InvalidParameterType(getString);
        }
    }

    public double getDouble(final String sectionName, final String parameterName) throws InvalidParameterName, InvalidSectionName, InvalidParameterType {
        existenceOfPair(sectionName, parameterName);
        String getString = "";
        try {
            for (Section section: fileContent){
                for (Parameter parameter : section.getParameters()) {
                    if (parameter.getName().equals(parameterName)) {
                        getString = parameter.getValue();
                        break;
                    }
                    if (getString != ""){
                        break;
                    }
                }
            }
            return Double.parseDouble(getString);
        } catch (Exception ex) {
            throw new InvalidParameterType(getString);
        }
    }

}