package eu.iba.auto_test.converterbnb.dao.services.impl;

import eu.iba.auto_test.converterbnb.controller.data.TaskData;
import eu.iba.auto_test.converterbnb.dao.model.Task;
import eu.iba.auto_test.converterbnb.dao.repository.sql.TaskDocumentSqlFunction;
import eu.iba.auto_test.converterbnb.dao.services.TaskDocumentServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskDocumentServiceDaoImpl implements TaskDocumentServiceDao {

    private final DataSource ds;

    @Autowired
    public TaskDocumentServiceDaoImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public List<Task> findAll(TaskData data) {
        Map<String, Object> param = createParamSql(data);
        return new TaskDocumentSqlFunction(ds, param).executeByNamedParam(param);
    }

    private Map<String, Object> createParamSql(TaskData data) {
        Map<String, Object> param = new HashMap<>();
        param.put("rkkId", data.getRkkId());
        return param;
    }

}
