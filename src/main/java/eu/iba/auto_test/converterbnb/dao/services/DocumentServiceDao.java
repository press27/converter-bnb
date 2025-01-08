package eu.iba.auto_test.converterbnb.dao.services;

import eu.iba.auto_test.converterbnb.dao.model.DocumentCategoryConstants;

public interface DocumentServiceDao {

    void saveAllByOne();

    void saveAllByList();

    void saveRangeByOneTestV1();

    void saveRangeByListTestV1();

    void saveAllByType(DocumentCategoryConstants documentCategoryConstants);

    void saveAllByTypeAndNextId(DocumentCategoryConstants documentCategoryConstants, Long nextId);

//    void saveOne(Long rkkId);

}
