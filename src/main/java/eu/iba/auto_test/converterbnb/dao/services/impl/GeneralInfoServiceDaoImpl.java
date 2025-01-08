package eu.iba.auto_test.converterbnb.dao.services.impl;

import eu.iba.auto_test.converterbnb.controller.data.AttachmentData;
import eu.iba.auto_test.converterbnb.controller.data.ProcessAttachmentTaskData;
import eu.iba.auto_test.converterbnb.controller.data.TaskData;
import eu.iba.auto_test.converterbnb.dao.model.*;
import eu.iba.auto_test.converterbnb.dao.repository.sql.*;
import eu.iba.auto_test.converterbnb.dao.repository.sql.model.TaskGeneral;
import eu.iba.auto_test.converterbnb.dao.repository.sql.model.TaskJobGeneral;
import eu.iba.auto_test.converterbnb.dao.repository.sql.task.model.Task;
import eu.iba.auto_test.converterbnb.dao.services.AttachmentDocumentServiceDao;
import eu.iba.auto_test.converterbnb.dao.services.GeneralInfoServiceDao;
import eu.iba.auto_test.converterbnb.dao.services.TaskServiceDao;
import eu.iba.auto_test.converterbnb.utils.AttachmentUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.*;

@Service
public class GeneralInfoServiceDaoImpl implements GeneralInfoServiceDao {

    public static final Logger log = LoggerFactory.getLogger(GeneralInfoServiceDaoImpl.class);

    private final DataSource ds;
    private final AttachmentDocumentServiceDao attachmentDocumentServiceDao;
    private final TaskServiceDao taskServiceDao;

    @Autowired
    public GeneralInfoServiceDaoImpl(DataSource ds, AttachmentDocumentServiceDao attachmentDocumentServiceDao, TaskServiceDao taskServiceDao) {
        this.ds = ds;
        this.attachmentDocumentServiceDao = attachmentDocumentServiceDao;
        this.taskServiceDao = taskServiceDao;
    }

    @Override
    public void findAllEmployeeAndAttachmentReview(Document document, TaskData data) {
        Map<String, Object> param = createParamSql(data);
        List<TaskGeneral> taskParents = new ReviewTaskParentSqlFunction(ds, param).executeByNamedParam(param);
        for (TaskGeneral taskParent : taskParents) {
            saveEmployee(document.getEmployeesAccess(), taskParent.getAuthor());
            saveAttachment(document, attachmentDocumentServiceDao.findAllInTask(new AttachmentData(taskParent.getId(), AttachType.E)));
            getInfoTaskJob(document, taskParent.getId());
            findAllTaskChildren(document, taskParent);
        }
    }

    @Override
    public void findAllEmployeeAndAttachmentRegistration(Document document, TaskData data) {
        Map<String, Object> param = createParamSql(data);
        List<TaskGeneral> taskParents = new RegistrationTaskParentSqlFunction(ds, param).executeByNamedParam(param);
        for (TaskGeneral taskParent : taskParents) {
            saveEmployee(document.getEmployeesAccess(), taskParent.getAuthor());
            saveAttachment(document, attachmentDocumentServiceDao.findAllInTask(new AttachmentData(taskParent.getId(), AttachType.E)));
            getInfoTaskJob(document, taskParent.getId());
            findAllTaskChildren(document, taskParent);
        }
    }

    @Override
    public void findAllEmployeeAndAttachmentByProcess(Document document, ProcessAttachmentTaskData data) {
        Map<String, Object> param = createParamEmployeeByProcessSql(data);
        List<TaskGeneral> taskParents = new ProcessAttachmentTaskParentSqlFunction(ds, param).executeByNamedParam(param);
        for (TaskGeneral taskParent : taskParents) {
            saveEmployee(document.getEmployeesAccess(), taskParent.getAuthor());
            saveAttachment(document, attachmentDocumentServiceDao.findAllInTask(new AttachmentData(taskParent.getId(), AttachType.E)));
            getInfoTaskJob(document, taskParent.getId());
            findAllTaskChildren(document, taskParent);
        }
    }

    @Override
    public void findAllAttachmentByTask(Document document, Long rkkId) {
        List<Task> tasks = taskServiceDao.findAllByRkkId(rkkId);
        for (Task task : tasks) {
            saveAttachment(document, attachmentDocumentServiceDao.findAllInTask(new AttachmentData(task.getId(), AttachType.E)));
            findAllAttachmentTaskChildren(document, task.getId());
        }
    }

    private void findAllAttachmentTaskChildren(Document document, Long taskParentId) {
        List<Task> tasks = taskServiceDao.findAllChildTaskByParentTaskId(taskParentId);
        for (Task task : tasks) {
            saveAttachment(document, attachmentDocumentServiceDao.findAllInTask(new AttachmentData(task.getId(), AttachType.E)));
            findAllAttachmentTaskChildren(document, task.getId());
        }
    }

    private void findAllTaskChildren(Document document, TaskGeneral taskParent) {
        if(taskParent != null && taskParent.getId() != null){
            Map<String, Object> param = createParamTaskChildSql(taskParent.getId());
            List<TaskGeneral> taskChildren = new ReviewTaskChildSqlFunction(ds, param).executeByNamedParam(param);
            for (TaskGeneral taskChild : taskChildren) {
                saveEmployee(document.getEmployeesAccess(), taskChild.getAuthor());
                saveAttachment(document, attachmentDocumentServiceDao.findAllInTask(new AttachmentData(taskChild.getId(), AttachType.E)));
                getInfoTaskJob(document, taskChild.getId());
                findAllTaskChildren(document, taskChild);
            }
        }
    }

    private void getInfoTaskJob(Document document, Long taskId){
        Map<String, Object> param = createParamTaskJobSql(taskId);
        List<TaskJobGeneral> taskJobGenerals = new TaskJobEmployeeSqlFunction(ds, param).executeByNamedParam(param);
        for (TaskJobGeneral taskJob : taskJobGenerals) {
            saveEmployee(document.getEmployeesAccess(), taskJob.getEmployee());
        }
    }

    private void saveAttachment(Document document, Set<AttachmentDocument> attachmentDocuments){
        if(attachmentDocuments != null){
            document.getAttachmentDocuments().addAll(AttachmentUtils.deleteAttachmentWithNullData(attachmentDocuments));
        }
    }

    private void saveEmployee(Set<Employee> employeesAccess, Employee employee){
        if(employee != null){
            employeesAccess.add(employee);
        }
    }

    private Map<String, Object> createParamEmployeeByProcessSql(ProcessAttachmentTaskData data) {
        Map<String, Object> param = new HashMap<>();
        param.put("docCardIds", data.getDocCardIds());
        return param;
    }


    private Map<String, Object> createParamTaskChildSql(Long taskId) {
        Map<String, Object> param = new HashMap<>();
        param.put("taskId", taskId);
        return param;
    }

    private Map<String, Object> createParamTaskJobSql(Long taskId) {
        Map<String, Object> param = new HashMap<>();
        param.put("taskId", taskId);
        return param;
    }

    private Map<String, Object> createParamSql(TaskData data) {
        Map<String, Object> param = new HashMap<>();
        if(data.getRkkId() != null) {
            param.put("rkkId", data.getRkkId());
        }
        return param;
    }
}
