package eu.iba.auto_test.converterbnb.dao.services;

import eu.iba.auto_test.converterbnb.controller.data.TaskExecutorsData;
import eu.iba.auto_test.converterbnb.dao.model.TaskExecutors;

import java.util.List;
import java.util.Set;

public interface TaskExecutorsServiceDao {

    Set<TaskExecutors> findAll(TaskExecutorsData data);

}
