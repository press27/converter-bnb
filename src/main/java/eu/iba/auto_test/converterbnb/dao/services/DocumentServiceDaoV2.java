package eu.iba.auto_test.converterbnb.dao.services;

import eu.iba.auto_test.converterbnb.dao.model.DocumentCategoryConstants;

import java.util.List;

public interface DocumentServiceDaoV2 {

    void saveOneByTypeAndId(DocumentCategoryConstants documentCategoryConstants, Long id);

    void saveOneByTypeAndIds(DocumentCategoryConstants documentCategoryConstants, List<Long> ids);

}
