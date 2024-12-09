package eu.iba.auto_test.converterbnb.dao.services.impl;

import eu.iba.auto_test.converterbnb.dao.repository.sql.task.ChildTaskSqlFunction;
import eu.iba.auto_test.converterbnb.dao.repository.sql.task.TaskJobSqlFunction;
import eu.iba.auto_test.converterbnb.dao.repository.sql.task.TaskSqlFunction;
import eu.iba.auto_test.converterbnb.dao.repository.sql.task.model.Task;
import eu.iba.auto_test.converterbnb.dao.repository.sql.task.model.TaskJob;
import eu.iba.auto_test.converterbnb.dao.services.TaskServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskServiceDaoImpl implements TaskServiceDao {

    private final DataSource ds;

    @Autowired
    public TaskServiceDaoImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public List<Task> findAllByRkkId(Long rkkId) {
        Map<String, Object> param = createParamTaskSql(rkkId);
        return new TaskSqlFunction(ds, param).executeByNamedParam(param);
    }

    @Override
    public List<Task> findAllChildTaskByParentTaskId(Long parentTaskId) {
        Map<String, Object> param = createParamTaskChildSql(parentTaskId);
        return new ChildTaskSqlFunction(ds, param).executeByNamedParam(param);
    }

    @Override
    public List<TaskJob> findAllTaskJobByTaskId(Long taskId) {
        Map<String, Object> param = createParamTaskJobSql(taskId);
        return new TaskJobSqlFunction(ds, param).executeByNamedParam(param);
    }

    private Map<String, Object> createParamTaskSql(Long rkkId) {
        Map<String, Object> param = new HashMap<>();
        param.put("rkkId", rkkId);
        return param;
    }

    private Map<String, Object> createParamTaskChildSql(Long parentTaskId) {
        Map<String, Object> param = new HashMap<>();
        param.put("parentTaskId", parentTaskId);
        return param;
    }

    private Map<String, Object> createParamTaskJobSql(Long taskId) {
        Map<String, Object> param = new HashMap<>();
        param.put("taskId", taskId);
        return param;
    }
}
