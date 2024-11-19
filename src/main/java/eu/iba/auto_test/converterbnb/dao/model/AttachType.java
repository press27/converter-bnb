package eu.iba.auto_test.converterbnb.dao.model;

//Тип вложения
public enum AttachType {

    // Документ
    E("E"),

    // Задача
    T("T"),

    // Задание
    J("J"),

    // Папка
    F("F"),

    // Запись справочника
    R("R"),

    // Ссылка (на документ, задачу, задание, папку или запись справочника)
    D("D");

    private final String value;

    private AttachType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
