package eu.iba.auto_test.converterbnb.dao.model;

public enum TaskTypeDirectum {

    R("Resolution"),
    S("SubordinateResolution"),
    P("ResolutionProject"),
    C("DocClause");

    private final String value;

    private TaskTypeDirectum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
