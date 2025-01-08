package eu.iba.auto_test.converterbnb.controller.filter;

import eu.iba.auto_test.converterbnb.dao.model.DocumentCategoryConstants;

public class DocumentFilter {

    private DocumentCategoryConstants documentCategoryConstants;
    private Long nextId;

    public DocumentFilter() {
    }

    public DocumentCategoryConstants getDocumentCategoryConstants() {
        return documentCategoryConstants;
    }

    public DocumentFilter setDocumentCategoryConstants(DocumentCategoryConstants documentCategoryConstants) {
        this.documentCategoryConstants = documentCategoryConstants;
        return this;
    }

    public Long getNextId() {
        return nextId;
    }

    public DocumentFilter setNextId(Long nextId) {
        this.nextId = nextId;
        return this;
    }
}
