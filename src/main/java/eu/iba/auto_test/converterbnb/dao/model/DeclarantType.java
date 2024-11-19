package eu.iba.auto_test.converterbnb.dao.model;

public enum DeclarantType {

    /**
     * Физическое лицо
     */
    INDIVIDUAL("Физическое лицо"),

    /**
     * Юридическое лицо
     */
    ENTITY("Юридическое лицо");

    private final String type;

    DeclarantType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
