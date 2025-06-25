package eu.iba.auto_test.converterbnb.dao.services.impl;

import eu.iba.auto_test.converterbnb.dao.model.additional.UpdateDocumentType;
import eu.iba.auto_test.converterbnb.dao.repository.sql.UpdateCitizenTypeSqlFunction;
import eu.iba.auto_test.converterbnb.dao.services.UpdateCitizenTypeServiceDao;
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
public class UpdateCitizenTypeServiceDaoImpl implements UpdateCitizenTypeServiceDao {

    public static final Logger log = LoggerFactory.getLogger(UpdateCitizenTypeServiceDaoImpl.class);

    private final DataSource ds;
    private final UploadService uploadService;

    @Autowired
    public UpdateCitizenTypeServiceDaoImpl(DataSource ds, UploadService uploadService) {
        this.ds = ds;
        this.uploadService = uploadService;
    }

    @Override
    public void saveAll(Integer count) {
        log.info("Start time: {}", Instant.now());
        Long nextId = 0L;
        List<UpdateDocumentType> documents = getDocs(nextId, count);
        while (!documents.isEmpty()) {
            documents = getUnique(documents);
            String strIds = documents.stream().map(UpdateDocumentType::getId).map(String::valueOf).collect(Collectors.joining(", "));
            try {
                nextId = documents.get(documents.size() - 1).getId();
                uploadService.updateDocumentTypeList(documents);
            } catch (Exception e) {
                saveToFileErrorDocIds(strIds);
                log.error("Process documents with ids: {}, {}", strIds, e.getMessage(), e);
            }
            documents = getDocs(nextId, count);
        }
        log.info("End time: {}", Instant.now());
    }

    private List<UpdateDocumentType> getDocs(Long nextId, Integer count){
        Map<String, Object> param = createParamSql(nextId, count);
        List<UpdateDocumentType> documents;
        documents = new UpdateCitizenTypeSqlFunction(ds, param).executeByNamedParam(param);
        return documents;
    }

    private Map<String, Object> createParamSql(Long nextId, Integer count) {
        Map<String, Object> param = new HashMap<>();
        param.put("count", count);
        param.put("nextId", nextId);
        return param;
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
