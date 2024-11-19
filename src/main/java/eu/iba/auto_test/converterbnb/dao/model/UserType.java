package eu.iba.auto_test.converterbnb.dao.model;

public enum UserType {

    USER("USER"),
    SYSTEM("SYSTEM");

    private final String value;

    private UserType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
