package eu.iba.auto_test.converterbnb.dao.services.impl;

import eu.iba.auto_test.converterbnb.controller.data.AttachmentData;
import eu.iba.auto_test.converterbnb.dao.model.AttachmentDocument;
import eu.iba.auto_test.converterbnb.dao.repository.sql.AttachmentDocumentSqlFunction;
import eu.iba.auto_test.converterbnb.dao.repository.sql.AttachmentTaskSqlFunction;
import eu.iba.auto_test.converterbnb.dao.services.AttachmentDocumentServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class AttachmentDocumentServiceDaoImpl implements AttachmentDocumentServiceDao {

    private final DataSource ds;

    @Autowired
    public AttachmentDocumentServiceDaoImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public Set<AttachmentDocument> findAll(AttachmentData data) {
        Map<String, Object> param = createParamSql(data);
        return (Set<AttachmentDocument>) new AttachmentDocumentSqlFunction(ds, param).executeByNamedParam(param);
    }

    @Override
    public Set<AttachmentDocument> findAllInTask(AttachmentData data) {
        Map<String, Object> param = createParamSql(data);
        return (Set<AttachmentDocument>) new AttachmentTaskSqlFunction(ds, param).executeByNamedParam(param);
    }

    private Map<String, Object> createParamSql(AttachmentData data) {
        Map<String, Object> param = new HashMap<>();
        if(data.getRkkId() != null) {
            param.put("rkkId", data.getRkkId()); //doc
        }
        if(data.getAttachType() != null) {
            param.put("attachType", data.getAttachType().getValue()); //task
        }
        if(data.getTaskId() != null) {
            param.put("taskId", data.getTaskId()); //task
        }
        if(data.getName() != null && !data.getName().isEmpty()) {
            param.put("name", "%" + data.getName() + "%") ; //name для Листа ознакомления
        }
        return param;
    }
}
