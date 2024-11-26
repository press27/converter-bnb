package eu.iba.auto_test.converterbnb.dao.services.impl;

import eu.iba.auto_test.converterbnb.controller.data.TaskExecutorsData;
import eu.iba.auto_test.converterbnb.dao.model.TaskExecutors;
import eu.iba.auto_test.converterbnb.dao.repository.sql.TaskExecutorsSqlFunction;
import eu.iba.auto_test.converterbnb.dao.services.TaskExecutorsServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.*;

@Service
public class TaskExecutorsServiceDaoImpl implements TaskExecutorsServiceDao {

    private final DataSource ds;

    @Autowired
    public TaskExecutorsServiceDaoImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public Set<TaskExecutors> findAll(TaskExecutorsData data) {
        Map<String, Object> param = createParamSql(data);
        List<TaskExecutors> taskExecutorsList = new TaskExecutorsSqlFunction(ds, param).executeByNamedParam(param);
        return new HashSet<>(taskExecutorsList);
    }

    private Map<String, Object> createParamSql(TaskExecutorsData data) {
        Map<String, Object> param = new HashMap<>();
        param.put("taskId", data.getTaskId());
        return param;
    }
}
