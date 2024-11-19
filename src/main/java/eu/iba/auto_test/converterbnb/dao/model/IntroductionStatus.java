package eu.iba.auto_test.converterbnb.dao.model;

public enum IntroductionStatus {

    IN_WORK("В работе"),
    COMPLETED("Выполнено"),
    DISCONTINUED("Прекращено");

    private final String value;

    IntroductionStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
