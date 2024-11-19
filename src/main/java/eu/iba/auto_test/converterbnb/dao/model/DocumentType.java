package eu.iba.auto_test.converterbnb.dao.model;

// Вид документа
public class DocumentType {

    // Id вида документа
    private Long documentTypeId;

    // Наименование вида документа
    private String documentType;

    public DocumentType() {
    }

    public Long getDocumentTypeId() {
        return documentTypeId;
    }

    public DocumentType setDocumentTypeId(Long documentTypeId) {
        this.documentTypeId = documentTypeId;
        return this;
    }

    public String getDocumentType() {
        return documentType;
    }

    public DocumentType setDocumentType(String documentType) {
        this.documentType = documentType;
        return this;
    }
}
