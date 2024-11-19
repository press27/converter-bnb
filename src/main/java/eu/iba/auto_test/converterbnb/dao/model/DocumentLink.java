package eu.iba.auto_test.converterbnb.dao.model;

import java.util.Objects;

public class DocumentLink {

    // Идентификатор записи
    private Long id;

    // Идентификатор ркк документа
    private Long docRkkId;

    // Идентификатор ркк документа на который ссылается текущий документ
    private Long docRkkLinkId;

    // Тип ссылки
    private DocumentLinkType documentLinkType;

    public DocumentLink() {
    }

    public Long getId() {
        return id;
    }

    public DocumentLink setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getDocRkkId() {
        return docRkkId;
    }

    public DocumentLink setDocRkkId(Long docRkkId) {
        this.docRkkId = docRkkId;
        return this;
    }

    public Long getDocRkkLinkId() {
        return docRkkLinkId;
    }

    public DocumentLink setDocRkkLinkId(Long docRkkLinkId) {
        this.docRkkLinkId = docRkkLinkId;
        return this;
    }

    public DocumentLinkType getDocumentLinkType() {
        return documentLinkType;
    }

    public DocumentLink setDocumentLinkType(DocumentLinkType documentLinkType) {
        this.documentLinkType = documentLinkType;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentLink that = (DocumentLink) o;
        return Objects.equals(id, that.id) && Objects.equals(docRkkId, that.docRkkId) && Objects.equals(docRkkLinkId, that.docRkkLinkId) && documentLinkType == that.documentLinkType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, docRkkId, docRkkLinkId, documentLinkType);
    }
}
