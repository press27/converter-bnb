package eu.iba.auto_test.converterbnb.dao.model;

public enum TaskExecutorsStatusDirectum {

    I("Init"),
    W("Work"),
    K("Control"),
    C("Complete"),
    N("Notice");

    private final String value;

    private TaskExecutorsStatusDirectum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
