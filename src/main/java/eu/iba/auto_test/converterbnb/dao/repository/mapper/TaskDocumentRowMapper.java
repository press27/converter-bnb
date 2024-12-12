package eu.iba.auto_test.converterbnb.dao.repository.mapper;

import eu.iba.auto_test.converterbnb.dao.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class TaskDocumentRowMapper implements RowMapper<Task> {

    public static final Logger log = LoggerFactory.getLogger(TaskDocumentRowMapper.class);

    @Override
    public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            Task model = new Task();
            model.setId(rs.getLong("id"));
            long rkkId = rs.getLong("rkkId");
            long rkkCitizenId = rs.getLong("rkkCitizenId");
            if (rkkId != 0) {
                model.setRkkId(rkkId);
            } else {
                model.setRkkId(rkkCitizenId);
            }
            model.setTypeDirectum(convertType(rs.getString("taskType")));
            model.setType(TaskType.GENERAL);
            model.setTaskNumber(rs.getString("taskNumber"));

            Timestamp createDate = rs.getTimestamp("createDate");
            if(createDate != null){
                model.setCreateDate(createDate.toInstant());
            }
            String taskText = rs.getString("taskText");
            if(taskText != null && !taskText.isEmpty()) {
                model.setTaskText(rs.getString("taskText"));
            } else {
                model.setTaskText("Поручение");
            }

            Timestamp planedDateEnd = rs.getTimestamp("planedDateEnd");
            if(planedDateEnd != null){
                model.setPlanedDateEnd(planedDateEnd.toInstant());
            } else {
                if(createDate != null){
                    model.setPlanedDateEnd(createDate.toInstant());
                }
            }
            Timestamp realDateEnd = rs.getTimestamp("realDateEnd");
            if(realDateEnd != null){
                model.setRealDateEnd(realDateEnd.toInstant());
            } else {
                if(createDate != null){
                    model.setRealDateEnd(createDate.toInstant());
                }
            }
            model.setTaskStatusDirectum(convertStatus(rs.getString("taskStatus")));
            model.setTaskStatus(TaskStatus.EXECUTED);

            Long authorId = rs.getObject("authorId", Long.class);
            if(authorId != null && authorId > 0) {
                Employee author = new Employee();
                author.setEmployeeId(authorId);
                author.setEmployeeFullName(rs.getString("authorFullName"));
                author.setEmployeeLoginAD(rs.getString("authorLoginAD"));
                model.setAuthor(author);
            }

            model.setParentId(rs.getLong("parentId"));
            return model;
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    private TaskTypeDirectum convertType(String type) {
        try {
            if (type != null && !type.isEmpty()) {
                return switch (type) {
                    case "S" -> TaskTypeDirectum.S;
                    case "P" -> TaskTypeDirectum.P;
                    case "C" -> TaskTypeDirectum.C;
                    default -> TaskTypeDirectum.R;
                };
            }
            return TaskTypeDirectum.R;
        } catch (Exception ex){
            log.error(ex.getMessage(),ex);
            return TaskTypeDirectum.R;
        }
    }

    private TaskStatusDirectum convertStatus(String status) {
        try {
            if (status != null && !status.isEmpty()) {
                return switch (status) {
                    case "I" -> TaskStatusDirectum.I;
                    case "W" -> TaskStatusDirectum.W;
                    case "K" -> TaskStatusDirectum.K;
                    case "N" -> TaskStatusDirectum.N;
                    default -> TaskStatusDirectum.C;
                };
            }
            return TaskStatusDirectum.C;
        } catch (Exception ex){
            log.error(ex.getMessage(),ex);
            return TaskStatusDirectum.C;
        }
    }
}
