package eu.iba.auto_test.converterbnb.dao.repository.mapper;

import eu.iba.auto_test.converterbnb.dao.model.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class TaskRowMapper implements RowMapper<Task> {
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
            model.setTaskText(rs.getString("taskText"));

            Timestamp planedDateEnd = rs.getTimestamp("planedDateEnd");
            if(planedDateEnd != null){
                model.setPlanedDateEnd(planedDateEnd.toInstant());
            }
            Timestamp realDateEnd = rs.getTimestamp("realDateEnd");
            if(realDateEnd != null){
                model.setRealDateEnd(realDateEnd.toInstant());
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
        return switch (type) {
            case "S" -> TaskTypeDirectum.S;
            case "P" -> TaskTypeDirectum.P;
            case "C" -> TaskTypeDirectum.C;
            default -> TaskTypeDirectum.R;
        };
    }

    private TaskStatusDirectum convertStatus(String status) {
        return switch (status) {
            case "I" -> TaskStatusDirectum.I;
            case "W" -> TaskStatusDirectum.W;
            case "K" -> TaskStatusDirectum.K;
            case "N" -> TaskStatusDirectum.N;
            default -> TaskStatusDirectum.C;
        };
    }
}
