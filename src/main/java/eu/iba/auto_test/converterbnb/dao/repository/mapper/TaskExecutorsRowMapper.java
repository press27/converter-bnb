package eu.iba.auto_test.converterbnb.dao.repository.mapper;

import eu.iba.auto_test.converterbnb.dao.model.*;
import eu.iba.auto_test.converterbnb.utils.UserUtils;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

public class TaskExecutorsRowMapper implements RowMapper<TaskExecutors> {
    @Override
    public TaskExecutors mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            TaskExecutors model = new TaskExecutors();
            model.setId(rs.getLong("id"));
            model.setTaskId(rs.getLong("taskId"));
            long rkkId = rs.getLong("rkkId");
            long rkkCitizenId = rs.getLong("rkkCitizenId");
            if (rkkId != 0) {
                model.setRkkId(rkkId);
            } else {
                model.setRkkId(rkkCitizenId);
            }

            Timestamp createDate = rs.getTimestamp("createDate");
            if(createDate != null){
                model.setCreateDate(createDate.toInstant());
            }
            model.setResponsible(Objects.equals(rs.getString("responsible"), "Д") ? Boolean.TRUE : Boolean.FALSE);

            Timestamp executedDate = rs.getTimestamp("executedDate");
            if(executedDate != null){
                model.setExecutedDate(executedDate.toInstant());
            }
            model.setStampText(rs.getString("stampText"));

            Employee executor = new Employee();
            Long employeeId = rs.getObject("employeeId", Long.class);
            if(employeeId != null) {
                executor.setEmployeeId(employeeId);
                executor.setEmployeeFullName(rs.getString("employeeFullName"));
                executor.setEmployeeLoginAD(rs.getString("employeeLoginAD"));
                executor.setUserType(UserUtils.getUserType(employeeId));
            } else {
                executor.setUserType(UserType.SYSTEM);
            }
            model.setExecutor(executor);

            model.setTaskExecutorsStatusDirectum(convertStatus(rs.getString("taskExecutorsStatusDirectum")));
            model.setNumber(rs.getLong("number"));
            model.setParentTaskId(rs.getLong("parentTaskId"));
            return model;
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    private TaskExecutorsStatusDirectum convertStatus(String status) {
        return switch (status) {
            case "I" -> TaskExecutorsStatusDirectum.I;
            case "W" -> TaskExecutorsStatusDirectum.W;
            case "K" -> TaskExecutorsStatusDirectum.K;
            case "N" -> TaskExecutorsStatusDirectum.N;
            default -> TaskExecutorsStatusDirectum.C;
        };
    }
}
