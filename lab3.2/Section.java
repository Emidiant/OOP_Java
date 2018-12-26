import java.util.ArrayList;
import java.util.List;

public class Section {
    private String sectionName;
    private List<Parameter> parameters;

    public Section(){
        parameters = new ArrayList<>();
    }

    public void add(Parameter parameter) {
        parameters.add(parameter);
    }

    public void setSectionName(String name) {
        this.sectionName = extractSection(name);
    }

    public void addAll(List<String> par) {
        for (String parameter: par) {
            Parameter param = new Parameter(parameter);
            this.parameters.add(param);
        }
    }

    private String extractSection(String str) {
        int posBegin = str.indexOf("[");
        int posEnd = str.indexOf("]");
        return str.substring(posBegin + 1, posEnd);
    }

    public String getSectionName() {
        return sectionName;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }
}
