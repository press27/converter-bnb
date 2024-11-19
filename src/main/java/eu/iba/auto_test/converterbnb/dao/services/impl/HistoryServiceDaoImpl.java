package eu.iba.auto_test.converterbnb.dao.services.impl;

import eu.iba.auto_test.converterbnb.controller.data.HistoryDocumentData;
import eu.iba.auto_test.converterbnb.controller.data.HistoryData;
import eu.iba.auto_test.converterbnb.controller.data.HistoryTaskData;
import eu.iba.auto_test.converterbnb.dao.model.History;
import eu.iba.auto_test.converterbnb.dao.repository.sql.HistoryDocumentSqlFunction;
import eu.iba.auto_test.converterbnb.dao.repository.sql.HistorySqlFunction;
import eu.iba.auto_test.converterbnb.dao.repository.sql.HistoryTaskSqlFunction;
import eu.iba.auto_test.converterbnb.dao.services.HistoryServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HistoryServiceDaoImpl implements HistoryServiceDao {

    private final DataSource ds;

    @Autowired
    public HistoryServiceDaoImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public List<History> findHistory(HistoryData data) {
        Map<String, Object> param = createParamSqlRKK(data);
        return new HistorySqlFunction(ds, param).executeByNamedParam(param);
    }

    @Override
    public List<History> findHistoryDocument(HistoryDocumentData data) {
        Map<String, Object> param = createParamSqlDocument(data);
        return new HistoryDocumentSqlFunction(ds, param).executeByNamedParam(param);
    }

    @Override
    public List<History> findHistoryTask(HistoryTaskData data) {
        Map<String, Object> param = createParamSqlTask(data);
        return new HistoryTaskSqlFunction(ds, param).executeByNamedParam(param);
    }

    private Map<String, Object> createParamSqlRKK(HistoryData data) {
        Map<String, Object> param = new HashMap<>();
        param.put("srcRecId", data.getSrcRecId());
        param.put("referenceTypeId", data.getReferenceTypeId());
        return param;
    }

    private Map<String, Object> createParamSqlDocument(HistoryDocumentData data) {
        Map<String, Object> param = new HashMap<>();
        param.put("rkkId", data.getRkkId());
        return param;
    }

    private Map<String, Object> createParamSqlTask(HistoryTaskData data) {
        Map<String, Object> param = new HashMap<>();
        param.put("taskId", data.getTaskId());
        return param;
    }


}
