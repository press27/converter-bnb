package eu.iba.auto_test.converterbnb.dao.model.additional;

import eu.iba.auto_test.converterbnb.dao.model.CitizenType;
import eu.iba.auto_test.converterbnb.dao.model.DocumentCategoryConstants;
import eu.iba.auto_test.converterbnb.dao.model.DocumentType;

import java.time.Instant;
import java.util.Objects;

public class UpdateDocumentType {

    // Id ркк XRecID
    private Long id;

    // Тип документа
    private DocumentCategoryConstants documentCategoryConstants;

    // Вид документа
    private DocumentType documentType;

    // Вид обращения
    private CitizenType citizenType;

    // Регистрационный номер
    private String regNumber;

    // Дата регистрации
    private Instant regDate;

    public UpdateDocumentType() {
    }

    public Long getId() {
        return id;
    }

    public UpdateDocumentType setId(Long id) {
        this.id = id;
        return this;
    }

    public DocumentCategoryConstants getDocumentCategoryConstants() {
        return documentCategoryConstants;
    }

    public UpdateDocumentType setDocumentCategoryConstants(DocumentCategoryConstants documentCategoryConstants) {
        this.documentCategoryConstants = documentCategoryConstants;
        return this;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public UpdateDocumentType setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
        return this;
    }

    public CitizenType getCitizenType() {
        return citizenType;
    }

    public UpdateDocumentType setCitizenType(CitizenType citizenType) {
        this.citizenType = citizenType;
        return this;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public UpdateDocumentType setRegNumber(String regNumber) {
        this.regNumber = regNumber;
        return this;
    }

    public Instant getRegDate() {
        return regDate;
    }

    public UpdateDocumentType setRegDate(Instant regDate) {
        this.regDate = regDate;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateDocumentType that = (UpdateDocumentType) o;
        return Objects.equals(id, that.id) && documentCategoryConstants == that.documentCategoryConstants && Objects.equals(documentType, that.documentType) && Objects.equals(citizenType, that.citizenType) && Objects.equals(regNumber, that.regNumber) && Objects.equals(regDate, that.regDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, documentCategoryConstants, documentType, citizenType, regNumber, regDate);
    }
}
