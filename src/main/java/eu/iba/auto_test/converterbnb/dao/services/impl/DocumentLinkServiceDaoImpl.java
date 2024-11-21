package eu.iba.auto_test.converterbnb.dao.services.impl;

import eu.iba.auto_test.converterbnb.dao.model.DocumentLink;
import eu.iba.auto_test.converterbnb.dao.repository.sql.DocumentLinkInRKKSqlFunction;
import eu.iba.auto_test.converterbnb.dao.repository.sql.DocumentLinkSqlFunction;
import eu.iba.auto_test.converterbnb.dao.services.DocumentLinkServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.*;

@Service
public class DocumentLinkServiceDaoImpl implements DocumentLinkServiceDao {

    private final DataSource ds;
    private final UploadService uploadService;

    @Autowired
    public DocumentLinkServiceDaoImpl(DataSource ds, UploadService uploadService) {
        this.ds = ds;
        this.uploadService = uploadService;
    }

    @Override
    public void saveAll() {
        Long nextId = 0L;
        Map<String, Object> param = createParamSql(nextId);
        List<DocumentLink> links = new DocumentLinkSqlFunction(ds, param).executeByNamedParam(param);
        while (!links.isEmpty()){
            for (DocumentLink link : links) {
                uploadService.uploadDocumentLink(link);
                nextId = link.getId();
            }
            param = createParamSql(nextId);
            links = new DocumentLinkSqlFunction(ds, param).executeByNamedParam(param);
        }

        nextId = 0L;
        param = createParamSql(nextId);
        links = new DocumentLinkInRKKSqlFunction(ds, param).executeByNamedParam(param);
        while (!links.isEmpty()){
            for (DocumentLink link : links) {
                uploadService.uploadDocumentLink(link);
                nextId = link.getId();
            }
            param = createParamSql(nextId);
            links = new DocumentLinkInRKKSqlFunction(ds, param).executeByNamedParam(param);
        }
    }

    private Map<String, Object> createParamSql(Long nextId) {
        Map<String, Object> param = new HashMap<>();
        param.put("nextId", nextId);
        return param;
    }
}