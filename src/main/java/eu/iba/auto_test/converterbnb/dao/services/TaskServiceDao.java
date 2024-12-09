package eu.iba.auto_test.converterbnb.dao.services;

import eu.iba.auto_test.converterbnb.dao.repository.sql.task.model.Task;
import eu.iba.auto_test.converterbnb.dao.repository.sql.task.model.TaskJob;

import java.util.List;

public interface TaskServiceDao {

    List<Task> findAllByRkkId(Long rkkId);

    List<Task> findAllChildTaskByParentTaskId(Long parentTaskId);

    List<TaskJob> findAllTaskJobByTaskId(Long taskId);

}
