package eu.iba.auto_test.converterbnb.dao.services.impl;

import eu.iba.auto_test.converterbnb.dao.model.DocumentCategoryConstants;
import eu.iba.auto_test.converterbnb.dao.model.additional.UpdateDocumentType;
import eu.iba.auto_test.converterbnb.dao.repository.sql.UpdateDocumentTypeGeneralSqlFunction;
import eu.iba.auto_test.converterbnb.dao.services.UpdateDocumentTypeGeneralServiceDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UpdateDocumentTypeGeneralServiceDaoImpl implements UpdateDocumentTypeGeneralServiceDao {

    public static final Logger log = LoggerFactory.getLogger(UpdateDocumentTypeGeneralServiceDaoImpl.class);

    private final DataSource ds;
    private final UploadService uploadService;

    @Autowired
    public UpdateDocumentTypeGeneralServiceDaoImpl(DataSource ds, UploadService uploadService) {
        this.ds = ds;
        this.uploadService = uploadService;
    }

    @Override
    public void saveAllByType(DocumentCategoryConstants documentCategoryConstants, Integer count) {
        log.info("Start time: {}", Instant.now());
        Long nextId = 0L;
        List<UpdateDocumentType> documents = getDocs(documentCategoryConstants, nextId, count);
        while (!documents.isEmpty()) {
            documents = getUnique(documents);
            String strIds = documents.stream().map(UpdateDocumentType::getId).map(String::valueOf).collect(Collectors.joining(", "));
            try {
                nextId = documents.get(documents.size() - 1).getId();
                //uploadService.updateDocumentType(documents);
            } catch (Exception e) {
                saveToFileErrorDocIds(strIds);
                log.error("Process documents with ids: {}, {}", strIds, e.getMessage(), e);
            }
            documents = getDocs(documentCategoryConstants, nextId, count);
        }
        log.info("End time: {}", Instant.now());
    }

    private List<UpdateDocumentType> getDocs(DocumentCategoryConstants documentCategoryConstants, Long nextId, Integer count){
        Map<String, Object> param = createParamSql(documentCategoryConstants, nextId, count);
        List<UpdateDocumentType> documents;
        documents = new UpdateDocumentTypeGeneralSqlFunction(ds, param).executeByNamedParam(param);
        return documents;
    }

    private Map<String, Object> createParamSql(DocumentCategoryConstants documentCategoryConstants, Long nextId, Integer count) {
        Map<String, Object> param = new HashMap<>();
        param.put("count", count);
        param.put("typeDoc", getTypeDoc(documentCategoryConstants));
        param.put("nextId", nextId);
        return param;
    }

    private String getTypeDoc(DocumentCategoryConstants documentCategoryConstants){
        if(documentCategoryConstants == DocumentCategoryConstants.INCOMING){
            return "В";
        } else if(documentCategoryConstants == DocumentCategoryConstants.OUTGOING){
            return "И";
        } else if(documentCategoryConstants == DocumentCategoryConstants.INTERN){
            return "У";
        }
        return null;
    }

    public static List<UpdateDocumentType> getUnique(List<UpdateDocumentType> documents){
        Map<Long, UpdateDocumentType> documentMap = new HashMap<>();
        for (UpdateDocumentType document: documents){
            documentMap.put(document.getId(), document);
        }

        List<UpdateDocumentType> uniqueDocuments = new ArrayList<>();
        for(Map.Entry<Long, UpdateDocumentType> item : documentMap.entrySet()){
            uniqueDocuments.add(item.getValue());
        }
        return uniqueDocuments;
    }

    public static void saveToFileErrorDocIds(String ids) {
        try {
            Path filePath = Paths.get("c:\\error-doc\\" + "error-file" + ".txt");
            if (!Files.exists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
            }
            String info = ids + ",";
            Files.write(filePath, info.getBytes(), java.nio.file.StandardOpenOption.APPEND, java.nio.file.StandardOpenOption.CREATE);
        } catch (Exception ex) {
            log.error(ex.getMessage(),ex);
        }
    }
}
