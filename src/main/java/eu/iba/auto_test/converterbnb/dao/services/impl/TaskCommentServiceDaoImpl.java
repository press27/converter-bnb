package eu.iba.auto_test.converterbnb.dao.services.impl;

import eu.iba.auto_test.converterbnb.dao.model.TaskComment;
import eu.iba.auto_test.converterbnb.dao.repository.sql.TaskCommentSqlFunction;
import eu.iba.auto_test.converterbnb.dao.repository.sql.task.model.Task;
import eu.iba.auto_test.converterbnb.dao.repository.sql.task.model.TaskJob;
import eu.iba.auto_test.converterbnb.dao.services.TaskCommentServiceDao;
import eu.iba.auto_test.converterbnb.dao.services.TaskServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.*;

@Service
public class TaskCommentServiceDaoImpl implements TaskCommentServiceDao {

    private final TaskServiceDao taskServiceDao;
    private final DataSource ds;

    @Autowired
    public TaskCommentServiceDaoImpl(TaskServiceDao taskServiceDao, DataSource ds) {
        this.taskServiceDao = taskServiceDao;
        this.ds = ds;
    }

    @Override
    public List<TaskComment> findAllByRkkId(Long rkkId) {
        List<TaskComment> allComments = new ArrayList<>();
        List<Task> tasks = taskServiceDao.findAllByRkkId(rkkId);
        saveComment(allComments, tasks);
        if(allComments.size() > 1){
            allComments.sort(Comparator.comparing(TaskComment::getCreateDate));
        }
        return allComments;
    }

    private void saveCommentChildTask(List<TaskComment> allComments, Long parentTaskId) {
        List<Task> tasks = taskServiceDao.findAllChildTaskByParentTaskId(parentTaskId);
        saveComment(allComments, tasks);
    }

    private void saveComment(List<TaskComment> allComments, List<Task> tasks){
        for (Task task : tasks) {
            Map<String, Object> param = createParamSql(task.getId());
            List<TaskComment> taskComments = new TaskCommentSqlFunction(ds, param).executeByNamedParam(param);
            for (TaskComment taskComment : taskComments) {
                if(Objects.equals(taskComment.getTaskId(), task.getId()) && taskComment.getJobId() == null){
                    taskComment.setTopic(task.getTopic());
                    taskComment.setStatus(task.getStatus());
                    break;
                }
            }
            List<TaskJob> taskJobs = taskServiceDao.findAllTaskJobByTaskId(task.getId());
            for (TaskJob taskJob : taskJobs) {
                for (TaskComment taskComment : taskComments) {
                    if(Objects.equals(taskComment.getJobId(), taskJob.getId())){
                        taskComment.setTopic(taskJob.getTopic());
                        taskComment.setStatus(taskJob.getStatus());
                    }
                }
            }
            allComments.addAll(taskComments);
            saveCommentChildTask(allComments, task.getId());
        }
    }

    private Map<String, Object> createParamSql(Long taskId) {
        Map<String, Object> param = new HashMap<>();
        param.put("taskId", taskId);
        return param;
    }



}
