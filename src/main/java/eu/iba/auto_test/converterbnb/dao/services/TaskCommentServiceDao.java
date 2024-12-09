package eu.iba.auto_test.converterbnb.dao.services;

import eu.iba.auto_test.converterbnb.dao.model.TaskComment;

import java.util.List;

public interface TaskCommentServiceDao {

    List<TaskComment> findAllByRkkId(Long rkkId);

}
