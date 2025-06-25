package eu.iba.auto_test.converterbnb.dao.services;

import eu.iba.auto_test.converterbnb.dao.model.DocumentCategoryConstants;

public interface UpdateDocumentTypeGeneralServiceDao {

    void saveAllByType(DocumentCategoryConstants documentCategoryConstants, Integer count);

    //    void saveByType(DocumentCategoryConstants documentCategoryConstants);

}
