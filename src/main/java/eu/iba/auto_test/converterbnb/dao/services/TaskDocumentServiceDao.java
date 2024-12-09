package eu.iba.auto_test.converterbnb.dao.services;

import eu.iba.auto_test.converterbnb.controller.data.TaskData;
import eu.iba.auto_test.converterbnb.dao.model.Task;

import java.util.List;

public interface TaskDocumentServiceDao {

    List<Task> findAll(TaskData data);

}
