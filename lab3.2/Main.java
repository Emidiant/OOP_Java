import Exceptions.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws InvalidParameterName, InvalidSectionName, FileNotFound, IOException, InvalidParameterType {
        Initialization ini = new Initialization("/Users/emidiant/IdeaProjects/lab3.2/src/main/java/test.ini");

        String checkString = ini.getString("ADC_DEV", "Driver");
        System.out.println("String value = " + checkString);
        int checkInt = ini.getInteger("DEBUG", "PlentySockMaxQSize");
        System.out.println("Integer value = " + checkInt);
        double checkDouble = ini.getDouble("ADC_DEV", "BufferLenSeconds");
        System.out.println("Double value = " + checkDouble);

        //Double.parseDouble("");
        //IniFile.parseFromFile(path);
        //представить файл в виде объекта, создать классы section и parameter, внутри которых производить инциализацию
    }
}