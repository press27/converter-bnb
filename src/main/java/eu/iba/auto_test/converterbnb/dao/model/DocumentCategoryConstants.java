package eu.iba.auto_test.converterbnb.dao.model;

public enum DocumentCategoryConstants {
    /**
     * внутрений
     */
    INTERN("Внутренний документ"),

    /**
     * входящий
     */
    INCOMING("Входящий документ"),

    /**
     * исходящий
     */
    OUTGOING("Исходящий документ"),

    /**
     * Обращения
     */
    APPEAL("Обращение");

    private final String docCategoryConstant;

    DocumentCategoryConstants(String docCategoryConstant) {
        this.docCategoryConstant = docCategoryConstant;
    }

    public String getDocCategoryConstant() {
        return docCategoryConstant;
    }

}
