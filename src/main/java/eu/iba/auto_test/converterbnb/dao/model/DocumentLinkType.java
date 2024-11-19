package eu.iba.auto_test.converterbnb.dao.model;

public enum DocumentLinkType {

    RECEIVED_IN_RESPONSE_TO("RECEIVED_IN_RESPONSE_TO"), //Получен в ответ на
    CREATE_IN_RESPONSE_TO("CREATE_IN_RESPONSE_TO"), //Создан в ответ на
    LINK_DOC("LINK_DOC"); //Ссылка на документ

    private final String value;

    DocumentLinkType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
